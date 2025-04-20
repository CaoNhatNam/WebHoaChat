package service;

import bean.Log;
import model.shop.Customer;
import model.shop.CustomerRowMapper;
import model.shop.TypeAcc;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

import java.util.List;

public class FacebookGoogleService {

    public static boolean checkExistAcc(Jdbi jdbi, String id_user_fb_gg, int typeAcc) {

        /*
            - b1: kiểm tra thông tin tài khoản FB/GG đã tồn tại trong hệ thống hay chưa
            - b2: trả về kết quả true/false
         */

        try {

            jdbi.registerRowMapper(new CustomerRowMapper()); // -> dùng để map danh sách các kết quả truy vấn thành các đối tượng Customer(username)

            if (typeAcc == TypeAcc.ACC_FACEBOOK) {

                List<Customer> customers = jdbi.withHandle(handle -> {
                    Query query = handle.createQuery("SELECT username FROM account_customers WHERE id_user_fb = :id_user_fb_gg AND id_type_acc= :typeAcc");
                    query.bind("id_user_fb_gg", id_user_fb_gg);
                    query.bind("typeAcc", typeAcc);
                    return query.mapTo(Customer.class).list();
                });

                if (customers.size() == 1) {
                    return true;
                }

            } else if (typeAcc == TypeAcc.ACC_GOOGLE) {

                List<Customer> customers = jdbi.withHandle(handle -> {
                    Query query = handle.createQuery("SELECT username FROM account_customers WHERE id_user_gg = :id_user_fb_gg AND id_type_acc= :typeAcc");
                    query.bind("id_user_fb_gg", id_user_fb_gg);
                    query.bind("typeAcc", typeAcc);
                    return query.mapTo(Customer.class).list();
                });

                if (customers.size() == 1) {
                    return true;
                }

            }
            return false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static int createAccPro(Jdbi jdbi, Customer infor, int typeAcc, Log logCreateAcc, Log logSignIn) {

          /*
            - b1 : ghi thông tin tài khoản FB/GG của customer vào bảng account_customers
            - b2 : ghi log tạo tài khoản vào bảng logs
            - b3 : ghi log đăng nhập vào bảng logs
            - b4 : trả về kết quả
                   + nếu = 1 thì tài khoản được tạo thành công và cho phép đăng nhập vào hệ thống
                   + nếu = 0 thì tài khoản không được tạo và không thể được đăng nhập vào hệ thống
                   + nếu = -1 thì có nghĩa là đã xảy ra lỗi

            Author : Minh Tuyên
         */

        try {
            return jdbi.withHandle(handle -> {

                Update update = null;
                if (typeAcc == TypeAcc.ACC_FACEBOOK) {

                    update = handle.createUpdate("INSERT INTO account_customers(id_status_acc,id_city,username,pass,full_name,email_customer,id_type_acc,id_user_fb) VALUES (1,0,:username,:pass,:full_name,:email_customer,:id_type_acc,:id_user_fb)");
                    update.bind("username", "FB" + infor.getId_user_fb())
                            .bind("pass", infor.getId_user_fb() + "@fb123")
                            .bind("full_name", infor.getFullname())
                            .bind("email_customer", infor.getEmail_customer())
                            .bind("id_type_acc", TypeAcc.ACC_FACEBOOK)
                            .bind("id_user_fb", infor.getId_user_fb());

                } else if (typeAcc == TypeAcc.ACC_GOOGLE) {

                    update = handle.createUpdate("INSERT INTO account_customers(id_status_acc,id_city,username,pass,full_name,email_customer,id_type_acc,id_user_gg) VALUES (1,0,:username,:pass,:full_name,:email_customer,:id_type_acc,:id_user_gg)");
                    update.bind("username", "GG" + infor.getId_user_gg())
                            .bind("pass", infor.getId_user_gg() + "@gg123")
                            .bind("full_name", infor.getFullname())
                            .bind("email_customer", infor.getEmail_customer())
                            .bind("id_type_acc", TypeAcc.ACC_GOOGLE)
                            .bind("id_user_gg", infor.getId_user_gg());

                }

                try {
                    handle.begin(); // bắt đầu một Transaction
                    int numRowInsert = update.execute();
                    if (numRowInsert > 0) {

                        logCreateAcc.insert(jdbi); // -> ghi log tạo tài khoản vào bảng Log
                        logSignIn.insert(jdbi); // -> ghi log đăng nhập vào bảng Log

                        handle.commit();
                        return 1;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    handle.rollback();
                    return -1;
                }

                return 0;
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    /*
        - Hàm này dùng để kiểm tra sự tồn tại của id_user_fb_gg trong Db
        - Nếu
            + tồn tại : => sẽ trả về id_user của tài khoản Fb/Gg đó
            + không tồn tại or xảy ra ngoại lệ : => sẽ trả về giá trị là -1
     */
    public static int checkExistAccReturnId(Jdbi jdbi, String id_user_fb_gg, int typeAcc) {
        int id_user_customer = -1;
        try {
            if (typeAcc == TypeAcc.ACC_FACEBOOK) {

                id_user_customer = jdbi.withHandle(handle -> {
                    Query query = handle.createQuery("SELECT id_user_customer FROM account_customers WHERE id_user_fb = :id_user_fb_gg AND id_type_acc= :typeAcc");
                    query.bind("id_user_fb_gg", id_user_fb_gg);
                    query.bind("typeAcc", typeAcc);
                    return query.mapTo(Integer.class).one();//--> Phương thức one() sẽ trả về giá trị của cột đầu tiên nếu có bản ghi được trả về từ câu truy vấn, ngược lại sẽ ném ra một ngoại lệ.
                });

            } else if (typeAcc == TypeAcc.ACC_GOOGLE) {

                id_user_customer = jdbi.withHandle(handle -> {
                    Query query = handle.createQuery("SELECT id_user_customer FROM account_customers WHERE id_user_gg = :id_user_fb_gg AND id_type_acc= :typeAcc");
                    query.bind("id_user_fb_gg", id_user_fb_gg);
                    query.bind("typeAcc", typeAcc);
                    return query.mapTo(Integer.class).one();
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id_user_customer;
    }

    /*
        - Hàm này dùng để tạo tài khoản mới trong Db dựa vào thông tin trả về từ Fb/Gg
        => kết quả trả về là id_user của tài khoản đó trong Db
        - nếu xảy ra ngoại lệ thì giá trị trả về sẽ là -1
        - ghi lại Log tạo tài khoản
     */
    public static int createAccProReturnId(Jdbi jdbi, Customer infor, int typeAcc, Log logCreateAcc) {
        int id_user = -1;
        try {
            if (typeAcc == TypeAcc.ACC_FACEBOOK) {
                id_user = jdbi.withHandle(handle -> {
                    Update update = handle.createUpdate("INSERT INTO account_customers(id_status_acc,id_city,username,pass,full_name,email_customer,id_type_acc,id_user_fb) VALUES (1,0,:username,:pass,:full_name,:email_customer,:id_type_acc,:id_user_fb)");
                    update.bind("username", "FB" + infor.getId_user_fb())
                            .bind("pass", infor.getId_user_fb() + "@fb123")
                            .bind("full_name", infor.getFullname())
                            .bind("email_customer", infor.getEmail_customer())
                            .bind("id_type_acc", TypeAcc.ACC_FACEBOOK)
                            .bind("id_user_fb", infor.getId_user_fb());

                    int row_insert = update.execute(); // trả về số dòng được insert vào bảng account_customers

                    if (row_insert == 1) {
                        Query query = handle.createQuery("SELECT LAST_INSERT_ID()"); // Khi chèn dữ liệu vào bảng trong MySQL bằng câu lệnh INSERT, một giá trị ID mới thường được tạo ra tự động cho mỗi bản ghi. Giá trị này thường được tạo ra bởi một trường ID tự động trong bảng hoặc một cột có thuộc tính AUTO_INCREMENT. Nếu bạn muốn truy vấn giá trị ID vừa được tạo ra để sử dụng cho các mục đích khác, bạn có thể sử dụng câu lệnh SELECT LAST_INSERT_ID().
                        return query.mapTo(Integer.class).one(); // trả về id_user ở dòng mới được thêm vào
                    }

                    return -1;
                });

            } else if (typeAcc == TypeAcc.ACC_GOOGLE) {
                id_user = jdbi.withHandle(handle -> {
                    Update update = handle.createUpdate("INSERT INTO account_customers(id_status_acc,id_city,username,pass,full_name,email_customer,id_type_acc,id_user_gg) VALUES (1,0,:username,:pass,:full_name,:email_customer,:id_type_acc,:id_user_gg)");
                    update.bind("username", "GG" + infor.getId_user_gg())
                            .bind("pass", infor.getId_user_gg() + "@gg123")
                            .bind("full_name", infor.getFullname())
                            .bind("email_customer", infor.getEmail_customer())
                            .bind("id_type_acc", TypeAcc.ACC_GOOGLE)
                            .bind("id_user_gg", infor.getId_user_gg());

                    int row_insert = update.execute(); // trả về số dòng được insert vào bảng account_customers

                    if (row_insert == 1) {
                        Query query = handle.createQuery("SELECT LAST_INSERT_ID()"); // Khi chèn dữ liệu vào bảng trong MySQL bằng câu lệnh INSERT, một giá trị ID mới thường được tạo ra tự động cho mỗi bản ghi. Giá trị này thường được tạo ra bởi một trường ID tự động trong bảng hoặc một cột có thuộc tính AUTO_INCREMENT. Nếu bạn muốn truy vấn giá trị ID vừa được tạo ra để sử dụng cho các mục đích khác, bạn có thể sử dụng câu lệnh SELECT LAST_INSERT_ID().
                        return query.mapTo(Integer.class).one(); // trả về id_user ở dòng mới được thêm vào
                    }

                    return -1;
                });
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            logCreateAcc.setUser_id(id_user + "");
            if (typeAcc == TypeAcc.ACC_FACEBOOK) {
                logCreateAcc.setContent("Tạo tài khoản bằng thông tin tài khoản Fb");
            } else if (typeAcc == TypeAcc.ACC_GOOGLE) {
                logCreateAcc.setContent("Tạo tài khoản bằng thông tin tài khoản Gg");
            }
            logCreateAcc.insert(jdbi);

        }
        return id_user;
    }

}
