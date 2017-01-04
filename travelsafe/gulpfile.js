var gulp = require('gulp');
var concat = require('gulp-concat');
var uglyfly = require('gulp-uglyfly');
var htmlreplace = require('gulp-html-replace');
var runSequence = require('run-sequence');
var clean = require('gulp-clean');
var concatVendor = require('gulp-concat-vendor');
var uglify = require('gulp-uglify');



var TMP_PATH = 'src/main/tmp/';
var DIST_PATH = 'src/main/dist/';


gulp.task('default', function() {
  // place code for your default task here
  console.log("Gulp default task!");
});


//JAVASCRIPT TASKS

//TODO - ADD BOWER COMPONENTS FROM index.html
gulp.task('concat-bower-components', function() {
  return  gulp.src(
       ["src/main/webapp/bower_components/jquery/dist/jquery.js",
        "src/main/webapp/bower_components/angular/angular.js",
        "src/main/webapp/bower_components/angular-aria/angular-aria.js",
        "src/main/webapp/bower_components/angular-bootstrap/ui-bootstrap-tpls.js",
        "src/main/webapp/bower_components/angular-cache-buster/angular-cache-buster.js",
        "src/main/webapp/bower_components/angular-cookies/angular-cookies.js",
        "src/main/webapp/bower_components/angular-local-storage/dist/angular-local-storage.js",
        "src/main/webapp/bower_components/angular-resource/angular-resource.js",
        "src/main/webapp/bower_components/angular-sanitize/angular-sanitize.js",
        "src/main/webapp/bower_components/angular-ui-router/release/angular-ui-router.js",
        "src/main/webapp/bower_components/angular-translate/angular-translate.js",
        "src/main/webapp/bower_components/bootstrap/dist/js/bootstrap.js",
        "src/main/webapp/bower_components/angular-modal-service/dst/angular-modal-service.min.js",
        "src/main/webapp/bower_components/angular-sanitize/angular-sanitize.js"])
       .pipe(concat('all-bower-components.js'))
       .pipe(gulp.dest(TMP_PATH));
});


//TODO - ADD ANGULAR APP FROM index.hmtl
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


gulp.task('concat-bower-and-angular', function() {
  return gulp.src(
    [
     TMP_PATH+'all-bower-components.js'
    ,TMP_PATH+'all-angular-app.js'
    ])
    .pipe(concat('all.js'))
    .pipe(gulp.dest(TMP_PATH));
});


gulp.task('uglyfly-app', function() {
  return gulp.src(TMP_PATH+'all.js')
    .pipe(uglyfly())
    .pipe(gulp.dest(DIST_PATH))
});


//TODO - CSS TASKS
/*gulp.task('concat-css-files', function() {
  return gulp.src(
    ['src/main/webappwebsite_theme/css/bootstrap.min.css',
    'src/main/webappwebsite_theme/css/font-awesome.min.css',
    'src/main/webappwebsite_theme/css/animate.css',
    'src/main/webappwebsite_theme/css/owl.carousel.css',
    'src/main/webappwebsite_theme/css/owl.theme.css',
    'src/main/webappwebsite_theme/css/prettyPhoto.css',
    'src/main/webappwebsite_theme/css/red.css',
    'src/main/webappwebsite_theme/css/custom.css',
    'src/main/webappwebsite_theme/css/responsive.css',
    'src/main/webappwebsite_theme/css/hcustom.css'
    ])
    .pipe(concat('all.css'))
    .pipe(gulp.dest('src/main/webapp/'));
});*/


//TODO - CSS TOO
gulp.task('html-replace', function() {
  return gulp.src('src/main/webapp/index.html')
    .pipe(
        htmlreplace({
        js: 'all.js'
        //,css: 'all.css'
        })
    )
    .pipe(gulp.dest(DIST_PATH));
});


gulp.task('delete-temp-files', function () {
    return gulp.src(TMP_PATH, {read: false})
        .pipe(clean());
});


gulp.task('copy-files-to-dist', function () {
    return gulp.src(
            [
             '!src/main/webapp/**/*.js',                // bez .js
             '!src/main/webapp/bower_components/**/*',  // random fajlovi koje bower komponente imaju
             '!src/main/webapp/index.html',             // index.html je izmenjen i vec prebacen
             '!src/main/webapp/bower.json',             // ne treba
             'src/main/webapp/**/*'                     // sve ostalo treba
             ]
             ).pipe(gulp.dest(DIST_PATH));
});


gulp.task('copy-files-to-dist-2', function () {
    return gulp.src(
            [
             'src/main/webapp/website_theme/**/*.js',                // bez .js, minifikovano je prebaceno
             ]
             ).pipe(gulp.dest(DIST_PATH+"website_theme"));
});



gulp.task('delete-tmp-files', function () {
    return gulp.src(TMP_PATH, {read: false})
        .pipe(clean());
});


gulp.task('build', function(callback) {
  runSequence('concat-bower-components',
              'concat-angular-app',
              'concat-bower-and-angular',
              'uglyfly-app',
              //'concat-css-files',
              'html-replace'
              ,'copy-files-to-dist'
              ,'copy-files-to-dist-2'
              ,'delete-tmp-files'
              );
});


