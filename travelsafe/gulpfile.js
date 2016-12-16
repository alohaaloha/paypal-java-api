var gulp = require('gulp');
var concat = require('gulp-concat');
var uglyfly = require('gulp-uglyfly');

gulp.task('default', function() {
  // place code for your default task here
  console.log("Gulp default task is working YEEEEY!");
});


gulp.task('t1', function() {
  //DON'T WORK
  return gulp.src('src/main/webapp/bower_components/**/*.js')
    .pipe(concat('all_bower.js'))
    .pipe(gulp.dest('src/main/webapp/temp/'));
});


gulp.task('t2', function() {
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
    .pipe(concat('all_angular.js'))
    .pipe(gulp.dest('src/main/webapp/temp/'));
});


gulp.task('t3', function() {
  return gulp.src(
    ['src/main/webapp/temp/all_bower.js'
    ,'src/main/webapp/temp/all_angular.js'])
    .pipe(concat('all.js'))
    .pipe(gulp.dest('src/main/webapp/temp/'));
});


gulp.task('t4', function() {
  gulp.src('src/main/webapp/temp/all.js')
    .pipe(uglyfly())
    .pipe(gulp.dest('src/main/webapp/dist/'))
});