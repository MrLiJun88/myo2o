$(function () {
    var shopId = getQueryString('shopId');
    var isEdit = shopId?true:false;
    var initUrl = '/myo2o/shopadmin/getShopInitInfo';
    var registerShopUrl = '/myo2o/shopadmin/registerShop';
    var shopInfoUrl = '/myo2o/shopadmin/getShopById?shopId=' + shopId;
    var editShopUrl = '/myo2o/shopadmin/modifyShop';

    if(!isEdit){
        getShopInitInfo();
    }
    else {
        getShopInfo(shopId);
    }
    /**
    * 根据店铺ID获取店铺信息：店铺分类和区域信息列表
    */
    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function(data) {
            // 数据存在
            if (data.success) {
                var shop = data.shop;
                // 赋值 要和shop实体类中的属性名保持一致
                $('#shop-name').val(shop.shopName);
                // 店铺名称不能修改
//				$('#shop-name').attr('disabled', 'disabled');
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);
                // 商品目录进行赋值 商品目录仅仅加载对应的目录，且不可编辑
                var shopCategory = '<option data-id="'
                    + shop.shopCategory.shopCategoryId + '" selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';
                $('#shop-category').html(shopCategory);
                // 设置为不可编辑
                $('#shop-category').attr('disabled', 'disabled');

                // 区域进行赋值 区域可以进行编辑，并且初始设置为后台对应的区域
                var tempShopAreaHtml = '';
                data.areaList.map(function(item, index) {
                    tempShopAreaHtml += '<option data-id="' + item.areaId
                        + '">' + item.areaName + '</option>';
                });
                $('#area').html(tempShopAreaHtml);
                // 初始设置为后台对应的区域
                $("#area option[data-id='" + shop.area.areaId + "']")
                    .attr("selected", "selected");
            } else {
                $.toast(data.errMsg);
            }
        })
    }

    /**
     * 从后台数据库中获取商铺信息与区域信息，填充至页面中
     */
    function getShopInitInfo() {
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
    }
    /**
     * 点击提交按钮时，向后台提交商铺信息
     */
    $('#submit').click(function () {
        var shop = {};
        if(isEdit){
            shop.shopId = shopId;
        }
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
            url:isEdit?editShopUrl:registerShopUrl,
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
});