$('nav .sideBtn').click(function(e) {
  e.preventDefault();
  $('nav .sideBtn ').toggleClass('on');

  if ($('.sideBtn').hasClass('on')) {
    $('nav .sideBtn ')
      .animate({ left: '270px' }, 200)
      .text('Close');
    $('nav').animate({ left: '0px' }, 200);
  } else {
    $('nav .sideBtn ')
      .animate({ left: '20px' }, 200)
      .text('OPEN');
    $('nav').animate({ left: '-300px' }, 200);
  }
});

let sectionTag = $('section');
let contentSide = $('.content-side > ul > li');

$(window).scroll(function() {
  var wScroll = $(this).scrollTop();
  if (wScroll >= sectionTag.eq(0).offset().top) {
    contentSide.removeClass('active');
    contentSide.eq(0).addClass('active');
  }
  if (wScroll >= sectionTag.eq(1).offset().top) {
    contentSide.removeClass('active');
    contentSide.eq(1).addClass('active');
  }
  if (wScroll >= sectionTag.eq(2).offset().top) {
    contentSide.removeClass('active');
    contentSide.eq(2).addClass('active');
  }
  if (wScroll >= sectionTag.eq(3).offset().top) {
    contentSide.removeClass('active');
    contentSide.eq(3).addClass('active');
  }
});

contentSide.click(function(e) {
  e.preventDefault();
  var target = $(this);
  var index = target.index();
  //alert(index);
  var section = sectionTag.eq(index);
  var offset = section.offset().top;
  $('html,body').animate({ scrollTop: offset });
});
