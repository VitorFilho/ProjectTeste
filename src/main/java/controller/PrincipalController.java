package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    Label lblNumero1,lblNumero2,lblResultado,lblSoma;

    @FXML
    TextField txtNumero1,txtNumero2;

    @FXML
    Button btnSomar,btnLimpar;

    @FXML
    public void onClickLimpar(ActionEvent event) {
        this.txtNumero1.setText("");
        this.txtNumero2.setText("");
        this.lblSoma.setText("");
        this.txtNumero1.requestFocus();//Cursor volta para primeiro campo
    }

    @FXML
    public void onClickSomar(ActionEvent event) {
        try{
            Double numero1 = Double.parseDouble(this.txtNumero1.getText());//Transformando String em Double
            Double numero2 = Double.parseDouble(this.txtNumero2.getText());

            numero1 += numero2;

            lblSoma.setText(new Double(numero1).toString());//transfromando o resultado em String
    }catch (NumberFormatException e){

            //Tratando o erro caso usuário não digite numero

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String textoErro = sw.toString();

            //Mostrando os erros

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Aconteceu um erro de conversão numérica.");

            Label label = new Label("Segue a pilha de exceção:");

            TextArea textArea = new TextArea(textoErro);
            textArea.setEditable(false);//Impossí´bilita edição de texto
            textArea.setWrapText(true);

            //Definindo Layout

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expConteudo = new GridPane();
            expConteudo.setMaxWidth(Double.MAX_VALUE);
            expConteudo.add(label, 0, 0);
            expConteudo.add(textArea, 0, 1);
            alerta.getDialogPane().setExpandableContent(expConteudo);
            alerta.showAndWait();

        }
    }

    public boolean onCloseQuery(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Pergunta");
        alerta.setHeaderText("Deseja sair do sistema");
        //Criando variaveis auxiliares
        ButtonType botaoNao = ButtonType.NO;
        ButtonType botaoSim = ButtonType.YES;
        alerta.getButtonTypes().setAll(botaoSim,botaoNao);//Pegando os botões e retorna uma lista de botões
        Optional<ButtonType> opcaoClicada = alerta.showAndWait();
        return opcaoClicada.get() == botaoSim ? true : false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

