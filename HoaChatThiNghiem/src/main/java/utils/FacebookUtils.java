package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;
import org.apache.http.client.fluent.Request;
import properties.FacebookProperties;

import java.io.IOException;

public class FacebookUtils {

    // Hàm getToken sử dụng để lấy token từ mã đã nhận được sau khi xác thực
    public static String getToken(final String code) throws IOException {
        // Tạo link để gửi yêu cầu lấy token
        String link = String.format(FacebookProperties.FACEBOOK_LINK_GET_TOKEN(),
                FacebookProperties.FACEBOOK_CLIENT_ID(),
                FacebookProperties.FACEBOOK_CLIENT_SECRET(),
                FacebookProperties.FACEBOOK_REDIRECT_URL(),
                code);

        // Gửi yêu cầu lấy token và lấy kết quả trả về dưới dạng chuỗi JSON
        String response_json_text = Request.Get(link).execute().returnContent().asString();

        // Chuyển kết quả trả về từ dạng chuỗi JSON sang đối tượng JsonObject sử dụng thư viện Gson
        JsonObject jobj = new Gson().fromJson(response_json_text, JsonObject.class);

        // Lấy giá trị của access_token trong JsonObject và loại bỏ ký tự ""
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

        return accessToken;
    }

    // Hàm getUserInfor sử dụng để lấy thông tin người dùng từ token
    public static User getUserInfor(String accessToken) {

        // Tạo đối tượng FacebookClient sử dụng token và app-secret để truy vấn dữ liệu
        FacebookClient fbClient = new DefaultFacebookClient(accessToken,
                FacebookProperties.FACEBOOK_CLIENT_SECRET(),
                Version.LATEST);

        return fbClient.fetchObject("me", User.class, Parameter.with("fields", "id, name, email, gender"));

        /*
        nếu không có đoạn code này
        Parameter.with("fields", "id, name, email")
        ==> chỉ lấy được thông tin cơ bản của người dùng như id,name
         */
    }


}
