$(function(){

    // ��ȡshopId
    var shopId = getQueryString("shopId");
    // ���̹����url
    var shopInfoUrl = '/myo2o/shopadmin/getShopManagementInfo?shopId=' + shopId;

    $.getJSON(shopInfoUrl,function (data) {
        // �����̨����redirect=true,����ת��̨�����õ�url
        if(data.redirect){
            window.location.href = data.url;
        }else{
            // �����̨����redirect=false��������shopId���� ��ť���ó��������ԣ����༭���̣�
            if (data.shopId != undefined && data.shopId != null){
                shopId = data.shopId;
            }
            $('#shopInfo').attr('href','/myo2o/shopadmin/shopoperation?shopId=' + shopId);
            // $('#productCategory').attr('href','/myo2o/shopadmin/productcategorymanagement');
        }
    });
});