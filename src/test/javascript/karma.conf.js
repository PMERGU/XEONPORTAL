// Karma configuration
// http://karma-runner.github.io/0.10/config/configuration-file.html

module.exports = function (config) {
    config.set({
        // base path, that will be used to resolve files and exclude
        basePath: '../../',

        // testing framework to use (jasmine/mocha/qunit/...)
        frameworks: ['jasmine'],

        // list of files / patterns to load in the browser
        files: [
            // bower:js
            'main/webapp/bower_components/jquery/dist/jquery.js',
            'main/webapp/bower_components/angular/angular.js',
            'main/webapp/bower_components/angular-aria/angular-aria.js',
            'main/webapp/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
            'main/webapp/bower_components/angular-cache-buster/angular-cache-buster.js',
            'main/webapp/bower_components/angular-cookies/angular-cookies.js',
            'main/webapp/bower_components/angular-local-storage/dist/angular-local-storage.js',
            'main/webapp/bower_components/angular-loading-bar/build/loading-bar.js',
            'main/webapp/bower_components/angular-resource/angular-resource.js',
            'main/webapp/bower_components/angular-sanitize/angular-sanitize.js',
            'main/webapp/bower_components/angular-ui-router/release/angular-ui-router.js',
            'main/webapp/bower_components/bootstrap-sass/assets/javascripts/bootstrap.js',
            'main/webapp/bower_components/json3/lib/json3.js',
            'main/webapp/bower_components/ngInfiniteScroll/build/ng-infinite-scroll.js',
            'main/webapp/bower_components/metisMenu/dist/metisMenu.js',
            'main/webapp/bower_components/jquery-ui/jquery-ui.js',
            'main/webapp/bower_components/iCheck/icheck.min.js',
            'main/webapp/bower_components/sweetalert/dist/sweetalert.min.js',
            'main/webapp/bower_components/angular-notify/dist/angular-notify.js',
            'main/webapp/bower_components/angular-animate/angular-animate.js',
            'main/webapp/bower_components/angular-ui-sortable/sortable.js',
            'main/webapp/bower_components/spin.js/spin.js',
            'main/webapp/bower_components/ladda/dist/ladda.min.js',
            'main/webapp/bower_components/angular-ladda/dist/angular-ladda.min.js',
            'main/webapp/bower_components/clockpicker/dist/jquery-clockpicker.js',
            'main/webapp/bower_components/jquery-mousewheel/jquery.mousewheel.js',
            'main/webapp/bower_components/php-date-formatter/js/php-date-formatter.js',
            'main/webapp/bower_components/eonasdan-bootstrap-/build/jquery.datetimepicker.full.min.js',
            'main/webapp/bower_components/angular-gravatar/build/angular-gravatar.js',
            'main/webapp/bower_components/datatables/media/js/jquery.dataTables.js',
            'main/webapp/bower_components/angular-datatables/dist/angular-datatables.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/bootstrap/angular-datatables.bootstrap.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/colreorder/angular-datatables.colreorder.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/columnfilter/angular-datatables.columnfilter.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/light-columnfilter/angular-datatables.light-columnfilter.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/colvis/angular-datatables.colvis.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/fixedcolumns/angular-datatables.fixedcolumns.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/fixedheader/angular-datatables.fixedheader.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/scroller/angular-datatables.scroller.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/tabletools/angular-datatables.tabletools.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/buttons/angular-datatables.buttons.js',
            'main/webapp/bower_components/angular-datatables/dist/plugins/select/angular-datatables.select.js',
            'main/webapp/bower_components/color-thief/src/color-thief.js',
            'main/webapp/bower_components/blob-polyfill/Blob.js',
            'main/webapp/bower_components/file-saver.js/FileSaver.js',
            'main/webapp/bower_components/angular-file-saver/dist/angular-file-saver.bundle.js',
            'main/webapp/bower_components/angular-ui-select/dist/select.js',
            'main/webapp/bower_components/angular-cache/dist/angular-cache.js',
            'main/webapp/bower_components/ng-file-upload/ng-file-upload.js',
            'main/webapp/bower_components/angular-mocks/angular-mocks.js',
            // endbower
            'main/webapp/scripts/app/app.js',
            'main/webapp/scripts/app/**/*.js',
            'main/webapp/scripts/components/**/*.+(js|html)',
            'test/javascript/spec/helpers/module.js',
            'test/javascript/spec/helpers/httpBackend.js',
            'test/javascript/**/!(karma.conf).js'
        ],


        // list of files / patterns to exclude
        exclude: [],

        preprocessors: {
            './**/*.js': ['coverage']
        },

        reporters: ['dots', 'jenkins', 'coverage', 'progress'],

        jenkinsReporter: {
            
            outputFile: '../target/test-results/karma/TESTS-results.xml'
        },

        coverageReporter: {
            
            dir: '../target/test-results/coverage',
            reporters: [
                {type: 'lcov', subdir: 'report-lcov'}
            ]
        },

        // web server port
        port: 9876,

        // level of logging
        // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['PhantomJS'],

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false,

        // to avoid DISCONNECTED messages when connecting to slow virtual machines
        browserDisconnectTimeout : 10000, // default 2000
        browserDisconnectTolerance : 1, // default 0
        browserNoActivityTimeout : 4*60*1000 //default 10000
    });
};
