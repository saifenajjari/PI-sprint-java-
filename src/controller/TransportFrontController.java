/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jfoenix.controls.JFXButton;
import entites.Ligne;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import services.LigneService;
import util.JavaMail;

/**
 * FXML Controller class
 *
 * @author asus_pc
 */
public class TransportFrontController implements Initializable {
    @FXML
    private AnchorPane anchor;
    ArrayList<Ligne> lignes = new ArrayList<>();
    private Ligne current_Ligne;
    
    public TransportFrontController() {
         LigneService ps = new LigneService();
        this.lignes = (ArrayList) ps.afficher();
    }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Ligne> obsl = FXCollections.observableArrayList(lignes);
        /**
         * ****** PAGINATION **********
         */
        Pagination pagination = new Pagination();
        // System.out.println("nbPieceReserved : "+pieces.size());

        if (lignes.size() % 3 > 0) {
            pagination.setPageCount((lignes.size() / 3) + 1);
        } else {
            pagination.setPageCount(lignes.size() / 3);
        }
         pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {  // every time when you click pagination button this method will be called

                return createPage(pageIndex, lignes);
            }
        });
        
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        anchor.getChildren().add(pagination);
        
        
    }
 public FlowPane createPage(int index, ArrayList<Ligne> lignes) {
        FlowPane flow = new FlowPane();
        Node[] nodes = new Node[4];
        int nbNode = -1;
        index++;
        int n = index + (index - 1) * 2 + 1;
        // System.out.println("n : "+n);
        int deb = n - 2;    
        for (int i = deb; i < deb + 3 && i != lignes.size(); i++) {
            nbNode++;
             try {

                    FXMLLoader loader = new FXMLLoader();

                    Pane root = loader.load(getClass().getResource("/views/SingleLigne.fxml").openStream());
                    SingleLigneController single = (SingleLigneController) loader.getController();
                    single.getInfo(lignes.get(i));
                    Ligne l1 = lignes.get(i);
                    SingleLigneController s1 = single;
                    JFXButton button = single.getButton();
                    if(l1.getPlacesDisponibles()==0){
                       button.setText("Complet");
                       button.setStyle("-fx-background-color: red;"); 
                       button.setDisable(true);
                    }
                     button.setOnAction(e -> {
                            int staticIdUser = 4;//va changer selon l'utilisateur connecté
                            this.current_Ligne = l1;
                            LigneService ls = new LigneService();
                            ls.reserver(current_Ligne.getId(),staticIdUser);
                            ls.decrementerNombreDeplace(current_Ligne.getId());
                            l1.setPlacesDisponibles(l1.getPlacesDisponibles()-1);
                            if(l1.getPlacesDisponibles()==0){
                             button.setText("Complet");
                            button.setStyle("-fx-background-color: red;"); 
                            button.setDisable(true);
                            }
                            s1.getInfo(l1);
                            showNotif();
                            String filePath = QRcode(current_Ligne);
                            String staticUserMail = "fekihmeyssen@gmail.com";//va changer selon l'utilisateur connecté
                        try {
                            JavaMail.sendMail(staticUserMail, filePath);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                            
                     });
                    nodes[nbNode] = root;
                    flow.getChildren().add(nodes[nbNode]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

           
        return flow;
                    
             }
         
           
    public void showNotif(){
         Image img = new Image("/uploads/success.png");
                ImageView imgV = new ImageView(img);
                imgV.setFitHeight(50);
                imgV.setFitWidth(50);
                
                  Notifications notif = Notifications.create()
                .title("  effectué")
                .text(" votre reservation a été effectué avec succés !")
                .graphic(imgV)
                .hideAfter(Duration.seconds(12))
                .position(Pos.TOP_CENTER)
                
                          .onAction(s->{
                         // System.out.println("notif clicked");
                          });
                notif.show();
    
    
    }
            
     public String QRcode(Ligne ligne){
             
   
     
        
        String myCodeText = "votre Ligne "+ligne.getNom()+" de "+ligne.getPointDepart()+" vers "+ligne.getPointArrive()+" à "+ligne.getDateDepart().getHour()+":"+ligne.getDateDepart().getMinute()+" a été reservé avec succes ! ";
        String filePath = "C:\\Users\\yosra\\Desktop\\Finale\\JardinEnfantJava\\src\\uploads\\"+ligne.getNom()+".png";
      
        int size = 250;
        String fileType = "png";
        File myFile = new File(filePath);
        try {
            //chn7a4ar el hint map mte3i eli chnestoki fyha 
            Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            //hintMap.put(EncodeHintType.MARGIN, 1);
            /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            // creation qr
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            ImageIO.write(image, fileType, myFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nYou have successfully created QR Code.");
        return filePath;
    }
    } 
            
            
            
            

