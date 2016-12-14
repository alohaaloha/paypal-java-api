var gulp = require('gulp');

gulp.task('default', ['concat_scripts', 'compress_all'], function() {
  // place code for your default task here
  console.log("Gulp default task is working YEEEEY!");
});

var concat = require('gulp-concat');
 
gulp.task('concat_scripts', function() {
  return gulp.src('src/main/webapp/**/*.js')
    .pipe(concat('all.js'))
    .pipe(gulp.dest('src/main/webapp/'));
});

var uglyfly = require('gulp-uglyfly');
 
gulp.task('compress_all', function() {
  gulp.src('src/main/webapp/all.js')
    .pipe(uglyfly())
    .pipe(gulp.dest('src/main/webapp/dist/'))
});