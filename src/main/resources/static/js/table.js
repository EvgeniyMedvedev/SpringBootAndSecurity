$(function () {

    const html = $('.table');

    $.getJSON('/users', function (users) {
        users.forEach(function (user) {

            let userElement = user['id'];

            html.append(
                "<tr>\n" +
                "   <th scope='row'>" + user['id'] + "</th>\n" +
                "   <td>" + user['name'] + "</td>\n" +
                "   <td>" + user['login'] + "</td>\n" +
                "   <td>" + user['password'] + "</td>\n" +
                "   <td>" + user['role'] + "</td>\n" +
                "   <td>\n" +
                "     <a href="+ user['id'] +"/delete/  class='btn btn-primary'>Удалить</a>\n" +
                "   </td>" +
                "   <td>\n" +
                "     <button class='btn btn-primary' data-toggle='modal' data-target=#exampleModal-" + userElement + ">\n" +
                "       Edit\n" +
                "    </button>\n" +
                "\n" +
                "                                <!-- Modal Edit -->\n" +
                "    <div class='modal fade' id=exampleModal-" + userElement +" tabindex='-1' role='dialog'\n" +
                "                                     aria-labelledby='exampleModalLabel' aria-hidden='true'>\n" +
                "                                <div class='modal-dialog' role='document'>\n" +
                "                                    <div class='modal-content'>\n" +
                "                                        <div class='modal-header'>\n" +
                "                                            <h5 class='modal-title' id='exampleModalLabel'>Edit User</h5>\n" +
                "                                            <button type='button' class='close' data-dismiss='modal'\n" +
                "                                                    aria-label='Close'>\n" +
                "                                                <span aria-hidden='true'>&times;</span>\n" +
                "                                            </button>\n" +
                "                                        </div>\n" +
                "                                        <form id='editModal' action=" + user['id'] + "/edit/ method='POST'>\n" +
                "                                            <input type='hidden' name='id' value=" + user['id'] + ">\n" +
                "                       <div class='modal-body'>\n" +
                "                                                  <strong>ID</strong>\n" +
                "                                                  <input class='d-flex justify-content-center form-control form-control-lg' type='text' value=" + user['id'] + " id='id' name='id' readOnly>\n" +
                "                                                  <strong>Username</strong>\n" +
                "                                                  <input class='d-flex justify-content-center form-control form-control-lg' type='text' value=" + user['name'] + " id='name' name='name' required>\n" +
                "                                                  <strong>Login</strong>\n" +
                "                                                  <input class='d-flex justify-content-center form-control form-control-lg' type='text' value=" + user['login'] + " id='login' name='login' required>\n" +
                "                                                  <strong>Password</strong>\n" +
                "                                                  <input class='d-flex justify-content-center form-control form-control-lg' type='text' value=" + user['password'] + " id='password' name='password' required>\n" +
                "                                                  <div class='modal-footer'>\n" +
                "                                           <button type='button' class='btn btn-secondary' data-dismiss='modal'>Close</button>\n" +
                "                                           <button type='submit' value='Edit' class='btn btn-primary'>Edit</button>\n" +
                "                                     </div>\n" +
                "                               </div>\n" +
                "                         </form>\n" +
                "                    </div>\n" +
                "               </div>\n" +
                "         </div>\n" +
                "    </td>" +
                "</tr>");
            // console.log(users);
        });

        // $('.table').append(html);
    });
});