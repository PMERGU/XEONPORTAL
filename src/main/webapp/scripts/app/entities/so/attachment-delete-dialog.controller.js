'use strict';

angular.module('portalApp')
    .controller('NewAttachmentDeleteController', function ($scope, $uibModalInstance, attachment, Attachment) {

        $scope.attachment = attachment;
        $scope.clear = function () {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (uuid) {
            console.log("deleting attachment for : " + uuid);
            Attachment.delete({uuid: uuid},
                function () {
                    $uibModalInstance.close(true);
                }
            );
        };
    });
