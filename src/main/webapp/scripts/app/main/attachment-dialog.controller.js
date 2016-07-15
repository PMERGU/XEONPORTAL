'use strict';

angular.module('portalApp').controller('AttachmentDialogController',
    ['$rootScope', '$scope', '$stateParams', '$uibModalInstance', 'entity', '$log', 'Upload', '$timeout', 'Attachment',
        function($rootScope, $scope, $stateParams, $uibModalInstance, entity, $log, Upload, $timeout, Attachment) {
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
        $scope.categories = [
            {nameSimple:'Condition of goods receipt'},
            {nameSimple:'Condition of goods delivery'},
            {nameSimple:'Container seal'},
            {nameSimple:'Truck seal'},
            {nameSimple:'TREM card'},
            {nameSimple:'Cartage advice'},
            {nameSimple:'other'}
        ];

        var onSaveSuccess = function (result) {
            result.for = $stateParams.for;
            $rootScope.$emit('portalApp:attachmentUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            // Party.save($scope.party, onSaveSuccess, onSaveError);
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        // HOW TO
        // https://github.com/danialfarid/ng-file-upload
        // upload on file select or drop
        $scope.upload = function (attachment) {
            var files = attachment.files;
            $log.debug($scope.attachment);
            var totalReturned = 0;
            $scope.isSaving = true;
            $scope.loadingWidth={'width': '10%'};
            if (files && files.length) {
                for (var i = 0; i < files.length; i++) {
                    var file = files[i];
                    if (!file.$error) {
                        Upload.upload({
                            url: 'api/attachments',
                            headers: {
                                'Content-Type': file.type
                            },
                            data: {
                                file: file,
                                deliveryNumber: $scope.attachment.deliveryNumber,
                                category: $scope.attachment.category.nameSimple,
                                description: $scope.attachment.description
                            }
                        }).then(function (resp) {
                            $log.debug('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
                            $scope.loadingWidth={'width': (parseInt($scope.loadingWidth.width.substr(0,2))+(100/files.length)) + '%'};
                            $log.debug($scope.loadingWidth);
                            totalReturned++;
                            if(files.length === totalReturned){
                                $scope.loadingWidth={'width': '100%'};
                                onSaveSuccess(resp);
                            }
                        }, function (resp) {
                            $log.error('Error status: ' + resp.status);
                            onSaveError(resp);
                        }, function (evt) {
                            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                            $log.debug('progress: ' + progressPercentage + '% ' + evt.config.data.file.name + '\n' + $scope.log);
                        });
                    }
                }
            }
        };

        $scope.uploadAsync = function (file) {
            // returns a promise
            upload.then(function (resp) {
                // file is uploaded successfully
                console.log('file ' + resp.config.data.file.name + 'is uploaded successfully. Response: ' + resp.data);
            }, function (resp) {
                // handle error
            }, function (evt) {
                // progress notify
                console.log('progress: ' + parseInt(100.0 * evt.loaded / evt.total) + '% file :' + evt.config.data.file.name);
            });
            upload.catch(errorCallback);
            upload.finally(callback, notifyCallback);
        }


        // // for multiple files:
        // $scope.uploadFiles = function (files) {
        //     if (files && files.length) {
        //         for (var i = 0; i < files.length; i++) {
        //             Upload.upload({..., data: {file: files[i]}, ...})...;
        //         }
        //         // or send them all together for HTML5 browsers:
        //         Upload.upload({..., data: {file: files}, ...})...;
        //     }
        // }
}]);
