$(function() {
    var loading = false;
    var maxItems = 20;
    var pageSize = 10;

    var listUrl = '/myo2o/front/listProductsByShop';

    var pageNum = 1;
    var shopId = getQueryString('shopId');
    var productCategoryId = '';
    var productName = '';

    var searchDivUrl = '/myo2o/front/listShopDetailPageInfo?shopId=' + shopId;

    getSearchDivData();
    addItems(pageSize, pageNum);

    function getSearchDivData() {
        var url = searchDivUrl;
        $.getJSON(url,
            function(data) {
                if (data.success) {
                    var shop = data.shop;
                    $('#shop-cover-pic').attr('src', shop.shopImg);
                    $('#shop-update-time').html(new Date(shop.lastEditTime).Format("yyyy-MM-dd"));
                    $('#shop-name').html(shop.shopName);
                    $('#shop-desc').html(shop.shopDesc);
                    $('#shop-addr').html(shop.shopAddr);
                    $('#shop-phone').html(shop.phone);

                    // ��������б�
                    var productCategoryList = data.productCategoryList;
                    var html = '';
                    productCategoryList
                        .map(function(item, index) {
                            html += '<a href="#" class="button" data-product-search-id='
                                + item.productCategoryId
                                + '>'
                                + item.productCategoryName
                                + '</a>';
                        });
                    $('#shopdetail-button-div').html(html);
                }
            });
    }

    /**
     * ���ݲ�ѯ������ȡ��ҳչʾ����Ʒ�б���Ϣ
     */
    function addItems(pageSize, pageIndex) {
        // ��������Ŀ��HTML
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
            + pageSize + '&productCategoryId=' + productCategoryId
            + '&productName=' + productName + '&shopId=' + shopId;
        // �趨���ط��������ں�̨ȥ���������ٴη��ʺ�̨���������ظ�����
        loading = true;
        // ���ʺ�̨��ȡ��Ӧ��ѯ�����µ���Ʒ�б�
        $.getJSON(url, function(data) {
            if (data.success) {
                // ��ȡ��ǰ��ѯ�����µ���Ʒ����
                maxItems = data.count;
                var html = '';
                // ������Ʒ�б���ƴ�ӳ���Ƭ�б�
                data.productList.map(function(item, index) {
                    html += '' + '<div class="card" data-product-id='
                        + item.productId + '>'
                        + '<div class="card-header">' + item.productName
                        + '</div>' + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + item.imgAddr + '" width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class="item-subtitle">' + item.productDesc
                        + '</div>' + '</div>' + '</li>' + '</ul>'
                        + '</div>' + '</div>' + '<div class="card-footer">'
                        + '<p class="color-gray">'
                        + new Date(item.lastEditTime).Format("yyyy-MM-dd")
                        + '����</p>' + '<span>����鿴</span>' + '</div>'
                        + '</div>';
                });
                $('.list-div').append(html);
                var total = $('.list-div .card').length;
                if (total >= maxItems) {
                    // ���ؼ�����ʾ��
                    $('.infinite-scroll-preloader').hide();
                }else{
                    $('.infinite-scroll-preloader').show();
                }
                pageNum += 1;
                loading = false;
                $.refreshScroller();
            }
        });
    }

    $(document).on('infinite', '.infinite-scroll-bottom', function() {
        if (loading)
            return;
        addItems(pageSize, pageNum);
    });

    $('#shopdetail-button-div').on(
        'click',
        '.button',
        function(e) {
            productCategoryId = e.target.dataset.productSearchId;
            if (productCategoryId) {
                if ($(e.target).hasClass('button-fill')) {
                    $(e.target).removeClass('button-fill');
                    productCategoryId = '';
                } else {
                    $(e.target).addClass('button-fill').siblings()
                        .removeClass('button-fill');
                }
                $('.list-div').empty();
                pageNum = 1;
                addItems(pageSize, pageNum);
            }
        });

    $('.list-div')
        .on('click',
            '.card',
            function(e) {
                var productId = e.currentTarget.dataset.productId;
                window.location.href = '/myo2o/front/productdetail?productId='
                    + productId;
            });
    // ��Ҫ��ѯ����Ʒ�������仯������ҳ�룬���ԭ�ȵ���Ʒ�б��������µ���Ʒ��ȥ��ѯ
    $('#search').on('change', function(e) {
        productName = e.target.value;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    $('#me').click(function() {
        $.openPanel('#panel-left-demo');
    });
    // ��ʼ��ҳ��
    $.init();
});