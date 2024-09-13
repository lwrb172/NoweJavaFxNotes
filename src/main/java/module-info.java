module com.chatroom.testchatjfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.chatroom.testchatjfx to javafx.fxml;
    exports com.chatroom.testchatjfx;
}