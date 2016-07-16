'use strict';

angular.module('portalApp')
    .controller('AttachmentDeleteController', function ($scope, $uibModalInstance, attachment, Attachment) {

        $scope.attachment = attachment;
        $scope.clear = function () {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (uuid) {
            console.log(uuid);
            Attachment.delete({uuid: uuid},
                function () {
                    $uibModalInstance.close(true);
                }
            );
        };
    });
