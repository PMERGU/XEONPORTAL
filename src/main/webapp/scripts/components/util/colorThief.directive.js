'use strict';

angular.module('portalApp')
    .directive('colorThief', function () {
        return {
            restrict: 'A',
            scope: {
                dominant: '=colorThiefDominant',
                palette: '=colorThiefPalette'
            },
            link: function (scope, element, attrs) {
                if (angular.uppercase(element[0].tagName) !== 'IMG') {
                    throw new Error('The colorThief directive has to be applied to an IMG tag.');
                }
                var colorThief = new ColorThief();

                // Allow configuring the image to retrieve CORS-enabled images.
                if (angular.isDefined(attrs.crossorigin) || angular.isDefined(attrs.crossOrigin)) {
                    element[0].crossOrigin = attrs.crossorigin || attrs.crossOrigin || 'Anonymous';
                }

                // Set it to undefined by default to allow the provider's default count overwrite this if needed
                var paletteCount = 3;

                // Everytime the image loads, calculate the colors again
                element.on('load', function () {
                    scope.$apply(function () {
                        if (attrs.colorThiefDominant) {
                            var rgb = colorThief.getColor(element[0]);
                            scope.dominant = rgbToHex(rgb[0], rgb[1], rgb[2]);
                        }

                        if (attrs.colorThiefPalette) {
                            var rgbPalette = colorThief.getPalette(element[0], paletteCount);
                            scope.palette = [];
                            $.each(rgbPalette, function(idx, rgb){
                                scope.palette.push(rgbToHex(rgb[0], rgb[1], rgb[2]));
                            })

                        }
                    });
                });

                function rgbToHex(r, g, b) {
                    return "#" + ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
                }
            }
        }
    });
