$(function () {
    var initUrl = '/myo2o/shopadmin/getShopInitInfo';
    var registerShopUrl = '/myo2o/shopadmin/registerShop';
    getShopInitInfo();
    /**
     * 从后台数据库中获取商铺信息与区域信息，填充至页面中
     */
    function getShopInitInfo() {
        /**
         * 获取从后台数据库中的店铺类别信息与区域列表信息
         */
        $.getJSON(initUrl,function (data) {
            if(data.success){
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item,index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">' +
                        item.shopCategoryName + '</option>';
                });
                $('#shop-category').html(tempHtml);
                data.areaList.map(function (item,index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' +
                        item.areaName + '</option>';
                });
                $('#area').html(tempAreaHtml);
            }
        });
        /**
         * 点击提交按钮时，向后台提交商铺信息
         */
        $('#submit').click(function () {
            var shop = {};
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId : $('#shop-category').find('option').not(function(){
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId : $('#area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            var shopImg = $('#shop-img')[0].files[0];
            var formData = new FormData();
            formData.append('shopImg',shopImg);
            formData.append('shopStr',JSON.stringify(shop));
            var verifyCodeActual = $('#j_kaptcha').val();
            if(! verifyCodeActual){
                $.toast('请输入验证码!');
                return;
            }
            formData.append('verifyCodeActual',verifyCodeActual);
            $.ajax({
                url:registerShopUrl,
                type:'POST',
                data: formData,
                contentType:false,
                processData:false,
                cache:false,
                success:function (data) {
                    if(data.success){
                        $.toast('提交成功');
                    }
                    else {
                        $.toast("提交失败" + data.errMsg);
                    }
                    $('#kaptcha_img').click();
                }
            });
        });
    }
});