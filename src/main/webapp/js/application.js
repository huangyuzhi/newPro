$(function() {
	var Accordion = function(el, multiple) {
		this.el = el || {};
		this.multiple = multiple || false;

		// Variables privadas
		var links = this.el.find('.link');
		// Evento
		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
	}

	Accordion.prototype.dropdown = function(e) {
		var $el = e.data.el;
			$this = $(this),
			$next = $this.next();

		$next.slideToggle();
		$this.parent().toggleClass('open');

		if (!e.data.multiple) {
			$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
		};
	}	

	var accordion = new Accordion($('#accordion'), false);
});
$(window).load(function(){

//  $('.selectpicker').selectpicker();

    /* 点击事件会在超链接跳转前发生 */
    $(".js-iframe-link").click(function(){
        var link = $(this).attr('href');
        $('#view').attr('src', link);
        var href = window.location.href;
        window.location.href = href.substr(0, href.indexOf('#')) + '#' + link;
        return false;
    });
	
	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
		$('.js-fa-toggle').toggleClass('fa-indent');
	});
	
	$('.js-map-bar-btn').click(function(e){
		e.preventDefault();
		$('.js-btn-list').toggleClass('hidden-xs');
	});
});
