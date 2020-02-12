$(function() {
    // ������ʺ�̨��ȡͷ���б��Լ�һ����������б��URL
    var url = '/myo2o/front/listMainPageInfo';

    // ���ʺ�̨��ȡͷ���б��Լ�һ���������
    $.getJSON(url, function (data) {
        if (data.success) {
            // ������������պ�̨���ݹ�����ͷ���б�����
            var headLineList = data.headLineList;
            var swiperHtml = '';
            // ����ͷ���б���ƴ�ӳ��ֲ�ͼ��
            headLineList.map(function (item, index) {
                swiperHtml += ''
                    + '<div class="swiper-slide img-wrap">'
                    +      '<img class="banner-img" src="'+ item.lineImg +'" alt="'+ item.lineName +'">'
                    + '</div>';
            });
            // ���ֲ�ͼ�鸳ֵ��ǰ��HTML�ռ�
            $('.swiper-wrapper').html(swiperHtml);
            // �����ֲ�ͼ�ֻ�ʱ��Ϊ1��
            $(".swiper-container").swiper({
                autoplay: 1000,
                // �û����ֲ�ͼ���в���ʱ���Ƿ��Զ�ֹͣautoplay
                autoplayDisableOnInteraction: false
            });
            // ��ȡ��̨���ݹ�����һ����������б�
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            // ����̨���ݹ�����һ����������б� ƴ��col-50 ����һ�е����
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                    +  '<div class="col-50 shop-classify" data-category='+ item.shopCategoryId +'>'
                    +      '<div class="word">'
                    +          '<p class="shop-title">'+ item.shopCategoryName +'</p>'
                    +          '<p class="shop-desc">'+ item.shopCategoryDesc +'</p>'
                    +      '</div>'
                    +      '<div class="shop-classify-img-warp">'
                    +          '<img class="shop-img" src="'+ item.shopCategoryImg +'">'
                    +      '</div>'
                    +  '</div>';
            });
            $('.row').html(categoryHtml);
        } else {
            alert(data.errMsg);
        }
    });

    // �ҵ�
    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    // ����ض��ķ���
    $('.row').on('click', '.shop-classify', function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/myo2o/front/shopList?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    });

    // �û���¼
    $('#login').click(function() {
        window.location.href = '/myo2o/admin/login';
    });

    // �޸�����
    $('#change-pwd').click(function() {
        window.location.href = '/myo2o/admin/changepwd';
    });

    // �˳���¼
    $('#log-out').click(function () {
        $.ajax({
            url : "/myo2o/user/logout",
            type : "post",
            contentType : false,
            processData : false,
            cache : false,
            success : function(data) {
                if (data.success) {
                    window.location.href = '/myo2o/admin/login';
                }
            },
            error : function(data, error) {
                alert(error);
            }
        });
    });

});
