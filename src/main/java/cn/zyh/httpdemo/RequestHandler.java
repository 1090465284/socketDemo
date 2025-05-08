package cn.zyh.httpdemo;

/**
 * @Description TODO
 * @Author ZhangYH
 * @Date 2025/5/9 00:12
 */
public class RequestHandler {
  /**
   * 将获取的请求报文封装成一个请求对象
   * @param requestMessage
   * @return
   */
  public static Request hand(String requestMessage){

    Request request = new Request();
    // 通过大量的截串获取对应信息
    String[] headerAndBody = requestMessage.split("\r\n\r\n");
    // 判断有没有请求体
    if(headerAndBody.length > 1){
      request.setBody(headerAndBody[1]);
    }
    // 将请求行和首部信息截取
    String[] lineAndHeader = headerAndBody[0].split("\r\n");
    String line = lineAndHeader[0];
    // 使用空格截取请求行信息
    String[] lines = line.split(" ");
    request.setType(lines[0]);
    request.setUri(lines[1]);
    request.setProtocol(lines[2]);
    // 遍历请求头
    for (int i = 1; i < lineAndHeader.length; i++) {
      String[] split = lineAndHeader[i].split(": ");
      request.addHeader(split[0],split[1]);
    }
    return request;
  }
}
