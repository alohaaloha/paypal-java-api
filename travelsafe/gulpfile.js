var gulp = require('gulp');
var concat = require('gulp-concat');
var uglyfly = require('gulp-uglyfly');
var htmlreplace = require('gulp-html-replace');
var runSequence = require('run-sequence');
var clean = require('gulp-clean');
var uglify = require('gulp-uglify');
var fs = require('fs');

// path variables
var TMP_PATH = 'src/main/tmp/';
var DIST_PATH = 'src/main/dist/';


gulp.task('default', function() {
  // place code for your default task here
  console.log("YEAAAAA Gulp default task!");
});


// CONCAT BOWER COMPONENTS FROM index.html
gulp.task('concat-bower-components', function() {
  return  gulp.src(
       [
        "src/main/webapp/bower_components/jquery/dist/jquery.js",
        "src/main/webapp/bower_components/angular/angular.js",
        "src/main/webapp/bower_components/angucomplete-alt/angucomplete-alt.js",
        "src/main/webapp/bower_components/angular-bootstrap/ui-bootstrap-tpls.js",
        "src/main/webapp/bower_components/angular-translate/angular-translate.js",
        "src/main/webapp/bower_components/angular-ui-router/release/angular-ui-router.js",
        "src/main/webapp/bower_components/bootstrap/dist/js/bootstrap.js",
        "src/main/webapp/bower_components/angular-messages/angular-messages.js",
        "src/main/webapp/assets/custom/js/angular-bootstrap-multiselect-customized.js"
        ]
        )
       .pipe(concat('bower-scripts.min.js'))
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
        ,"src/main/webapp/buy/edit-person-details.controller.js"
        ,"src/main/webapp/buy/type-of-risk.controller.js"
        ,'src/main/webapp/buy/buy.state.js'
        ,"src/main/webapp/buy/price.service.js"
        ,"src/main/webapp/buy/item.service.js"

        ,'src/main/webapp/paypal/status.controller.js'
        ,'src/main/webapp/paypal/status.service.js'
        ,'src/main/webapp/paypal/status.state.js'])
    .pipe(concat('app.min.js'))
    .pipe(gulp.dest(TMP_PATH));
});


// CONCAT WEBSITE THEME SCRIPTS
gulp.task('concat-theme-scripts', function() {
  return gulp.src(
        [
            "src/main/webapp/assets/js/jquery.js",
            "src/main/webapp/assets/js/bootstrap.min.js",
            "src/main/webapp/assets/js/owl.carousel.min.js",
            "src/main/webapp/assets/js/jquery.isotope.js",
            "src/main/webapp/assets/js/jquery.prettyPhoto.js",
            "src/main/webapp/assets/js/smooth-scroll.js",
            "src/main/webapp/assets/js/jquery.fancybox.pack.js?v=2.1.5",
            "src/main/webapp/assets/js/jquery.counterup.min.js",
            "src/main/webapp/assets/js/waypoints.min.js",
            "src/main/webapp/assets/js/jquery.bxslider.min.js",
            "src/main/webapp/assets/js/jquery.scrollTo.js",
            "src/main/webapp/assets/js/jquery.easing.1.3.js",
            "src/main/webapp/assets/js/jquery.singlePageNav.js",
            "src/main/webapp/assets/js/wow.min.js",

            "src/main/webapp/assets/js/custom.js"
        ])
    .pipe(concat('theme-scripts.min.js'))
    .pipe(gulp.dest(TMP_PATH));
});


// UGLYFLY 1.JS FILES
gulp.task('uglyfly-1', function() {
  return gulp.src(TMP_PATH+'bower-scripts.min.js')
    .pipe(uglyfly())
    .pipe(gulp.dest(DIST_PATH))
});

// UGLYFLY 2.JS FILES
gulp.task('uglyfly-2', function() {
  return gulp.src(TMP_PATH+'app.min.js')
    .pipe(uglyfly())
    .pipe(gulp.dest(DIST_PATH))
});

// UGLYFLY 3.JS FILES
gulp.task('uglyfly-3', function() {
  return gulp.src(TMP_PATH+'theme-scripts.min.js')
    .pipe(uglyfly())
    .pipe(gulp.dest(DIST_PATH))
});


// REPLACE LINES IN index.html
gulp.task('html-replace-1', function() {
  return gulp.src('src/main/webapp/index.html')
    .pipe(
        htmlreplace({
        bower: 'bower-scripts.min.js',
        angular: 'app.min.js'
        })
    )
    .pipe(gulp.dest(DIST_PATH));
});

// REPLACE LINES IN home.html
gulp.task('html-replace-2', function() {
  return gulp.src('src/main/webapp/home/home.html')
    .pipe(
        htmlreplace({
        theme: 'theme-scripts.min.js'
        })
    )
    .pipe(gulp.dest(DIST_PATH+"home/"));
});


// COPY OTHER FILES TO DIST FOLDER
gulp.task('copy-files-to-dist', function () {
    return gulp.src(
            [
             '!src/main/webapp/**/*.js',        // .min versions already copied
             '!src/main/webapp/bower.json',     // not necessary
             '!src/main/webapp/index.html',     // updated references
             '!src/main/webapp/home/home.html', // updated references
             'src/main/webapp/**/*'
             ]
             ).pipe(gulp.dest(DIST_PATH));
});


// DELETE TMP FOLDER
gulp.task('delete-tmp-folder', function () {
    return gulp.src(TMP_PATH, {read: false})
        .pipe(clean());
});


//----------------------------------------------------------------

// REMNAME webapp TO dev
gulp.task('rename-webapp-to-dev', function(done) {
  return fs.rename('src/main/webapp', 'src/main/dev', function (err) {
    if (err) {
      throw err;
    }
    done();
  });
});


// REMNAME dist TO webapp
gulp.task('rename-dist-to-webapp', function(done) {
  return fs.rename('src/main/dist', 'src/main/webapp', function (err) {
    if (err) {
      throw err;
    }
    done();
  });
});

//-------------------------------------------------------------------

//trying some stuff
// REMNAME webapp TO dist
gulp.task('rename-webapp-to-dist', function(done) {
  fs.rename('src/main/webapp', 'src/main/dist', function (err) {
    if (err) {
      throw err;
    }
    done();
  });
});


//trying some stuff
// REMNAME dev TO webapp
gulp.task('rename-dev-to-webapp', function(done) {
  fs.rename('src/main/dev', 'src/main/webapp', function (err) {
    if (err) {
      throw err;
    }
    done();
  });
});


//-----------------------------------------------------------

// MAIN TASK FOR BUILDING DIST FOLDER
gulp.task('build', function(callback) {
   runSequence(
               'concat-bower-components'
              ,'concat-angular-app'
              ,'concat-theme-scripts'
              ,'uglyfly-1'
              ,'uglyfly-2'
              ,'uglyfly-3'
              ,'html-replace-1'
              ,'html-replace-2'
              ,'copy-files-to-dist'
              ,'delete-tmp-folder',
              "rename-webapp-to-dev",
              "rename-dist-to-webapp"
              );
});

// NO NEED FOR THIS
// MAIN TASK FOR PROD - THIS REMOVES 'dev' webapp folder and replace it with 'min' version
gulp.task('prod', function(callback) {
  runSequence(
              "build",
              "rename-webapp-to-dev",
              "rename-dist-to-webapp"
              );
});


//trying some stuff
gulp.task('dev', function(callback) {
  runSequence(
              "rename-webapp-to-dist",
              "rename-dev-to-webapp"
              );
});
