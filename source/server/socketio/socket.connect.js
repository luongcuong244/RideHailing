const startSocketIO = (io) => {
    io.of("/booking").on("connection", (socket) => {

        console.log("New client connected");

        socket.on("request-a-ride", (data) => {
            console.log("request-a-ride", data);
        });

        socket.on("disconnect", () => {
            console.log("Client disconnected");
        });
    });
}

module.exports = startSocketIO;