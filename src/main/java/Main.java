import controller.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{//add exceção
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal.fxml"));
        Parent pane01 = loader.load();
        scene = new Scene (pane01);
        stage.setTitle("Somar Numeros");// ttulo da tela
        primaryStage.setScene(scene);//Atribuindo a cena para janela
        primaryStage.resizableProperty().setValue(Boolean.FALSE);//Manter tamanho inicial da tela

        //Carregando o controller da tela
            PrincipalController principalController = loader.getController();

        //Atribuir um evento a janela de fechar a tela
        primaryStage.setOnCloseRequest(e -> {
            if(principalController.onCloseQuery()){
                System.exit(0);//Sair do programa
            }else{
                e.consume();//Continua no programa
            }
        });
        primaryStage.show();
    }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[]args){
        launch(args);
    }
}
