let gulp = require('gulp');
let strip = require('gulp-strip-comments');
let sass = require('gulp-sass');
let browser = require('gulp-browser');

gulp.task('default', ['html', 'css', 'js', 'mjs', 'mcss']);

gulp.task('html', () => {
  gulp.src('templates/*.html')
    .pipe(gulp.dest('public/templates'));
  return gulp.src('index.html')
    .pipe(gulp.dest('public/'));
});

let dist = 'public';

let materialPaths = {
  materialJs: [
    'js/vendor/modules/**/*.js'
  ],
  materialCss: [
    'scss/vendors/modules/angular-material.css'
  ]
};

let sassPaths = {
  sass: [
    'scss/style.scss',
    'scss/vendors/modules/angular-material.scss',
  ]
};

gulp.task('mjs', () => {
  return gulp.src(materialPaths.materialJs)
    .pipe(gulp.dest(dist));
});

gulp.task('mcss', () => {
  return gulp.src(materialPaths.materialCss)
    .pipe(gulp.dest(dist));
});

gulp.task('css', () => {
  return gulp.src(sassPaths.sass)
    .pipe(sass())
    .pipe(gulp.dest('public'));
});

gulp.task('js', () => {
  return gulp.src('js/app.js')
    .pipe(browser.browserify())
    .pipe(gulp.dest('public/'));
});

gulp.task('watch', ['default'], () => {
  gulp.watch('*.html', ['html']);
  gulp.watch('scss/*.scss', ['css']);
  gulp.watch('js/*/*.js', ['js']);
  gulp.watch('js/*.js', ['js']);
  gulp.watch('templates/*.html', ['html']);
});