var gulp = require('gulp');
var concat = require('gulp-concat');
var uglyfly = require('gulp-uglyfly');
var htmlreplace = require('gulp-html-replace');
var runSequence = require('run-sequence');
var clean = require('gulp-clean');
var concatVendor = require('gulp-concat-vendor');
var uglify = require('gulp-uglify');

// path variables
var TMP_PATH = 'src/main/tmp/';
var DIST_PATH = 'src/main/dist/';


gulp.task('default', function() {
  // place code for your default task here
  console.log("Gulp default task!");
});


// CONCAT BOWER COMPONENTS FROM index.html
gulp.task('concat-bower-components', function() {
  return  gulp.src(
       ["src/main/webapp/bower_components/jquery/dist/jquery.js",
        "src/main/webapp/bower_components/angular/angular.js",
        "src/main/webapp/bower_components/angular-bootstrap/ui-bootstrap-tpls.js",
        "src/main/webapp/bower_components/angular-messages/angular-messages.js",
        "src/main/webapp/bower_components/angular-resource/angular-resource.js",
        "src/main/webapp/bower_components/angular-sanitize/angular-sanitize.js",
        "src/main/webapp/bower_components/angular-translate/angular-translate.js",
        "src/main/webapp/bower_components/angular-ui-router/release/angular-ui-router.js",
        "src/main/webapp/bower_components/bootstrap/dist/js/bootstrap.js"])
       .pipe(concat('all-bower-components.js'))
       .pipe(gulp.dest(TMP_PATH));
});


// CONCAT ANGULAR APP FROM index.html
gulp.task('concat-angular-app', function() {
  return gulp.src(
        ['src/main/webapp/app/app.module.js'
        ,'src/main/webapp/app/app.state.js'
        ,'src/main/webapp/navbar/navbar.controller.js'
        ,'src/main/webapp/home/home.controller.js'
        ,'src/main/webapp/buy/buy.controller.js'
        ,'src/main/webapp/buy/buy.state.js'
        ,'src/main/webapp/paypal/status.controller.js'
        ,'src/main/webapp/paypal/status.service.js'
        ,'src/main/webapp/paypal/status.state.js'])
    .pipe(concat('all-angular-app.js'))
    .pipe(gulp.dest(TMP_PATH));
});


// CONCAT WEBSITE THEME SCRIPTS
gulp.task('concat-theme-scripts', function() {
  return gulp.src(
        [
            "src/main/webapp/website_theme/js/jquery.js",
            "src/main/webapp/website_theme/js/bootstrap.min.js",
            "src/main/webapp/website_theme/js/owl.carousel.min.js",
            "src/main/webapp/website_theme/js/jquery.isotope.js",
            "src/main/webapp/website_theme/js/jquery.prettyPhoto.js",
            "src/main/webapp/website_theme/js/smooth-scroll.js",
            "src/main/webapp/website_theme/js/jquery.fancybox.pack.js?v=2.1.5",
            "src/main/webapp/website_theme/js/jquery.counterup.min.js",
            "src/main/webapp/website_theme/js/waypoints.min.js",
            "src/main/webapp/website_theme/js/jquery.bxslider.min.js",
            "src/main/webapp/website_theme/js/jquery.scrollTo.js",
            "src/main/webapp/website_theme/js/jquery.easing.1.3.js",
            "src/main/webapp/website_theme/js/jquery.singlePageNav.js",
            "src/main/webapp/website_theme/js/wow.min.js",
            "src/main/webapp/website_theme/js/gmaps.js",
            "src/main/webapp/website_theme/js/custom.js"
        ])
    .pipe(concat('all-theme-scripts.js'))
    .pipe(gulp.dest(TMP_PATH));
});


// UGLYFLY .JS FILES
gulp.task('uglyfly-1', function() {
  return gulp.src(TMP_PATH+'all-bower-components.js')
    .pipe(uglyfly())
    .pipe(gulp.dest(DIST_PATH))
});

// UGLYFLY .JS FILES
gulp.task('uglyfly-2', function() {
  return gulp.src(TMP_PATH+'all-angular-app.js')
    .pipe(uglyfly())
    .pipe(gulp.dest(DIST_PATH))
});

// UGLYFLY .JS FILES
gulp.task('uglyfly-3', function() {
  return gulp.src(TMP_PATH+'all-theme-scripts.js')
    .pipe(uglyfly())
    .pipe(gulp.dest(DIST_PATH))
});


// REPLACE LINES IN index.html
gulp.task('html-replace', function() {
  return gulp.src('src/main/webapp/index.html')
    .pipe(
        htmlreplace({
        bower: 'all-bower-components.js',
        angular: 'all-angular-app.js'
        })
    )
    .pipe(gulp.dest(DIST_PATH));
});

// REPLACE LINES IN home.html
gulp.task('html-replace', function() {
  return gulp.src('src/main/webapp/home/home.html')
    .pipe(
        htmlreplace({
        theme: 'all-theme-scripts.js'
        })
    )
    .pipe(gulp.dest(DIST_PATH));
});


// COPY OTHER FILES TO DIST FOLDER
gulp.task('copy-files-to-dist', function () {
    return gulp.src(
            [
             '!src/main/webapp/**/*.js',
             '!src/main/webapp/index.html',
             'src/main/webapp/**/*.css',
             'src/main/webapp/**/*.html'
             ]
             ).pipe(gulp.dest(DIST_PATH));
});


// DELETE TMP FILES
gulp.task('delete-tmp-files', function () {
    return gulp.src(TMP_PATH, {read: false})
        .pipe(clean());
});


// MAIN TASK FOR BUILDING DIST FOLDER
gulp.task('build', function(callback) {
  runSequence(
               'concat-bower-components'
              ,'concat-angular-app'
              ,'concat-theme-scripts'
              ,'uglyfly-1'
              ,'uglyfly-2'
              ,'uglyfly-3'
              ,'html-replace'
              ,'copy-files-to-dist'
              ,'delete-tmp-files'
              );
});


