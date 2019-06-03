import java.io.*;
import java.net.*;

class TCPServer {
 public static void main(String argv[]) throws Exception {
  char[] clientSentence= new char[1000];
  String capitalizedSentence;
  ServerSocket welcomeSocket = new ServerSocket(6789);

  while (true) {
  try{
  System.out.println("starting");
   Socket connectionSocket = welcomeSocket.accept();
   BufferedReader inFromClient =
    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
   DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
   while (inFromClient.ready() && (inFromClient.read(clientSentence, 0, 1000) != -1)) {
   System.out.println(new String(clientSentence).split(" ", 3)[1]);
   System.out.println(new String(clientSentence));
   }
   System.out.println("responding");
   PrintWriter pw = new PrintWriter(outToClient);
   		pw.print("HTTP/1.1 200 \r\n"); // Version & status code
        pw.print("Content-Type: application/json\r\n"); // The type of data
        pw.print("Access-Control-Allow-Origin: *\r\n");
        pw.print("X-Content-Type-Options: nosniff\r\n");
        pw.print("Access-Control-Allow-Headers: *\r\n");
        pw.print("Allow: OPTIONS, GET, HEAD, POST\r\n");
        pw.print("Connection: close\r\n"); // Will close stream
        pw.print("\r\n"); // End of headers
		pw.println("{\"id\":1,\"method\":\"object.deleteAll\",\"params\":[\"subscriber\"]}");
		pw.flush();
		pw.close();	
		System.out.println("finished");
   // fetch('http://localhost:6789').then((res) => res.json()).then(data => console.log(data)).catch(err => console.error(err));
   }catch(Exception e){
   	System.out.println(e);
   }
  }
 }
}
