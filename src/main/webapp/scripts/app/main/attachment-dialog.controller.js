'use strict';

angular.module('portalApp').controller('AttachmentDialogController',
    ['$rootScope', '$scope', '$stateParams', '$uibModalInstance', 'entity', '$log', 'Upload', 'UploadTools', 'Attachment',
        function($rootScope, $scope, $stateParams, $uibModalInstance, entity, $log, Upload, UploadTools, Attachment) {
            $scope.attachmentCategories = Attachment.queryCategories();
            Upload.setDefaults({ngfMinSize: 20000, ngfMaxSize:20000000});
            // $scope.submit = function() {
            //     if ($scope.form.file.$valid && $scope.file) {
            //         $scope.upload($scope.file);
            //     }
            // };

            // $scope.$watch('files', function () {
            //     $scope.upload($scope.files);
            // });
            // $scope.$watch('file', function () {
            //     if ($scope.file != null) {
            //         $scope.files = [$scope.file];
            //     }
            // });
            $scope.log = '';

        $scope.attachment = entity;

        var onSaveSuccess = function (result) {
            result.for = $stateParams.for;
            $rootScope.$emit('portalApp:attachmentUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function () {
            $scope.isSaving = false;
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        // HOW TO
        // https://github.com/danialfarid/ng-file-upload
        // upload on file select or drop
        $scope.upload = function(attachment){
            UploadTools.upload(attachment,
                function(success){
                    $scope.isSaving=false;
                    onSaveSuccess(success);
                },
                function(err){
                    onSaveError(err);
                },
                function(progress){
                    $log.debug(progress);
                    $scope.isSaving=true;
                    $scope.loadingWidth={'width':  progress.percentage + '%'};
                }
            );
        }
}]);
