$(document).ready(function (){
    loadBackGround();
});

/**
 * 变换页面
 */
function loadBackGround(){
    var img = Math.round(Math.random() * 10);
    var Img = new Array();
//需要加载的背景图片
    Img[0] = "https://img2.baidu.com/it/u=1506121011,1888356275&fm=26&fmt=auto&gp=0.jpg"
    Img[1] = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4165415060,2023874983&fm=26&gp=0.jpg"
    Img[2] = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3706677817,1678627265&fm=26&gp=0.jpg"
    Img[3] = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=518541639,428272303&fm=26&gp=0.jpg"
    Img[4] = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1482498295,222129637&fm=26&gp=0.jpg"
    Img[5] = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=357843698,3560590396&fm=26&gp=0.jpg"
    Img[6] = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.zhimg.com%2Fv2-0a760f7fe141e66bc765bd780d2e0a38_1440w.jpg%3Fsource%3D172ae18b&refer=http%3A%2F%2Fpic1.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633253145&t=66a6fc4ce509abc02e9d79ab3c1c9f1d"
    Img[7] = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4189220961,2869949368&fm=26&gp=0.jpg"
    Img[8] = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3004924597,1854631352&fm=26&gp=0.jpg"
    Img[9] = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww1.sinaimg.cn%2Fmw690%2F0074FH44ly1gt8ig166u8j30to0z843h.jpg&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633253145&t=642b297f521c6fee2c02238a7172b12f"
    Img[10] = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsearchfoto.ru%2Fimg%2FxyygpKbDS1y8pTjXRy83VS8rMS9fLSy3RL8mwz0yx9fcM0POIMipIDo409g7I8jIMLTC3qDIKT0n3SPTxUEvMLbAutzUyNgCzMmwNzSGsomJbQzCjIDnHNgUMwNx8W1OIMNBoQz1DAA.jpg&refer=http%3A%2F%2Fsearchfoto.ru&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633253145&t=e2250d692d56bae2215c438953d349ca"
    if(document.readyState === "loading" ||document.readyState === "interactive" ||document.readyState === "complete")
    {
        backGroundSetting(Img[img]);
    }


}
var ajaxFailMsg ="系统开了个小差，请稍后再试！！！";

/**
 * 用户登录
 */
function loginFuc() {

    var data ={
        //获取输入框用户名
        "username": $("#username").val(),
        //获取输入框密码
        "password": $("#password").val()
    };
    console.log(vailDate(data));
  if (vailDate(data))  {
    $.ajax({
        url: '/example/user/doLogin',
        type: 'POST',
        dataType: 'json',
        data: data,
        //传过来的data需要.data才可以获取当前对象。因为data是封装过的
    }).done(function (data) {
        if (data.code==200){
            $(location).attr('href','/example/user/userList');
        }else {
            alert(data.msg);
        }
    }).fail(function () {
        alert(ajaxFailMsg);
    });
  }
}

/**
 * 用户注册
 */
function registFuc() {
    var data = {
        username: $("#username").val(),
        password: $("#password").val(),
        phone: $("#phone").val(),
        email: $("#email").val()
    };
   if (vailDate(data)){
       $.ajax({
           url: '/example/user/doSaveUser',
           type: 'POST',
           dataType: 'json',
           data: data,
           //传过来的data需要.data才可以获取当前对象。因为data是封装过的
       }).done(function (data) {
           if (data.code == 200) {
               alert(data.msg);
               toList('/example/user/statusResult');
           } else {
               alert(data.msg);
           }
       }).fail(function () {
           alert(ajaxFailMsg);
       });
   }
}
