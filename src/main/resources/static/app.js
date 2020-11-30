var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#notifications").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({
            "user" : document.getElementById("login").value
        }

        , function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/user/'+document.getElementById("login").value+'/queue/reply', function(notification) {
                printNotification(JSON.parse(notification.body).message.replace(/(?:\r\n|\r|\n)/g, "<br>"));
            });
            sendName();

        });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/connect", {
    }, JSON.stringify({
        'name': document.getElementById("login").value,
        'toUser' : document.getElementById("login").value
    }));
}

function printNotification(message) {
    $("#notifications").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});