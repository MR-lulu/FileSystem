package util;

import javafx.scene.control.Alert;

/**
 * Created by MainasuK on 2016-11-28.
 */
public class SimpleErrorAlert extends Alert {

    public SimpleErrorAlert(String title, String header, String context) {
        super((AlertType.ERROR));
        setTitle(title);
        setHeaderText(header);
        setContentText(context);
    }

}
