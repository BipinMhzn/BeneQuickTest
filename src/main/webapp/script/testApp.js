angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {

            fetchData();

            function fetchData() {
                $http({method: 'GET', url: 'api/load-data'}).then(function (response) {
                    $scope.stateMembers = response.data;
                }, function (reason) {
                    console.log('error ' + reason)
                });
            }

            $scope.displayState = function(){
                $scope.selectedState = $scope.state[0].state;
            }

        }]);
