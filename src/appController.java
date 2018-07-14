import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class appController {
    @FXML
    private BorderPane scenePane;
    @FXML
    private Slider smoothnessSlider;
    @FXML
    private Slider currentPointSlider;
    @FXML
    private Button minus;

    static int WIDTH = 900;
    static int HEIGHT = 450;
    static int count = 0;
    Bezier bezier = new Bezier(WIDTH /2, HEIGHT / 2);

    public void initialize() {
        smoothnessSlider.valueProperty().addListener(observable -> {
            bezier.setSmoothness((int) smoothnessSlider.getValue());
            currentPointSlider.setMax(smoothnessSlider.getValue());
        });

        currentPointSlider.valueProperty().addListener(observable -> {
            bezier.setCurrentPoint((int) currentPointSlider.getValue());
        });

        Pane pane = new Pane();
        pane.setPrefSize(WIDTH,HEIGHT);

        Group root = new Group(pane,bezier.getBezierGroup());

        scenePane.setCenter(new SubScene(root, WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED));
        Background background = new Background(new BackgroundFill(Color.web("183D42"), CornerRadii.EMPTY, Insets.EMPTY));
        scenePane.setBackground(background);

        pane.setOnMouseClicked(event -> {
          addNodeLocation(event.getSceneX(),event.getSceneY());
        });
    }


    public void addNode() {

            bezier.addNode(WIDTH/2,HEIGHT/2);
            if (bezier.getNumOfNodes() > 3) {
                minus.setDisable(false);
            }
    }

    public void addNodeLocation(double x, double y) {
        bezier.addNode(x,y);
        if (bezier.getNumOfNodes() > 3) {
            minus.setDisable(false);
        }
    }

    public void removeNode() {
        if (bezier.getNumOfNodes() > 3) {
            bezier.removeNode();
        }
        if (bezier.getNumOfNodes() == 3) {
            minus.setDisable(true);
        }
    }

    public void toggleGuides(){
        bezier.toggleGuides();
    }

}
