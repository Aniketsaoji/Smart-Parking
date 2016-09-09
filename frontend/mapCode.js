$(function() {
      var googleMap = new google.maps.Map(document.getElementById('map'), {
                center: new google.maps.LatLng(32.712, -117.157491),
                zoom: 18,
                alt: "Current Parking Status",
                disableDefaultUI: false,
                zoomControl: false,
                mapTypeControl: false,
                scaleControl: false,
                streetViewControl: false,
                rotateControl: false,
                fullscreenControl: false,
                draggable: false,
                disableDoubleClickZoom: false,
                scrollwheel: false

              });

      //Dev key: AIzaSyDuNNvv4iy7BTgDj_MmF0o2f6OD-SGE41Y
      //settings variable to hold common information
      var symbols = [];
      var locaitonsURL = "https://smart-parking-v2.run.aws-usw02-pr.ice.predix.io/parkingDetails";

      var settings = {
        shape: google.maps.SymbolPath.BACKWARD_CLOSED_ARROW,
        shapeScale: 5,
        shapeStroke: 1
      }

      function symbol(color, lat, lng){
        console.log(color);
          return new google.maps.Marker({
                          position: new google.maps.LatLng(lat, lng),
                          icon: {
                            path: settings.shape,
                            scale: settings.shapeScale,
                            fillColor: color,
                            strokeWeight: settings.shapeStroke,
                            strokeColor: color,
                            fillOpacity: 1
                          },
                          draggable: false,
                          map: googleMap
                          });
      }
googleMap.addListener('click', function(event) {
          addMarker(event.latLng);
        });
function addMarker(location) {
        var marker = new google.maps.Marker({
          position: location,
          map: googleMap
        });
      }
      //returns a location
      setInterval(function(){
      $.ajax({
          url: locaitonsURL,
          success: function(response){
            var json = JSON.parse(response);
            updateMap(json.data);
          }
      });
    }, 2000)

      function updateMap(locs){
        for(var i=0;i<symbols.length;i++){
          symbols.pop().setMap(null);
        }
        for(var i=0;i<locs.length;i++){
          symbols.push(symbol(getColor(locs[i].state,locs[i].chance), locs[i].lat, locs[i].lng));
        }
      }

      function getColor(state, chance){
        if(state == "true")
        {
          return rgbToHex(mapVal(chance/10, 0, 1, 255, 0), mapVal(chance/10, 0, 1, 0, 255), 0);
        }
        return rgbToHex(0, mapVal(chance/10, 0, 1, 255, 0), mapVal(chance/10, 0, 1, 0, 255));
      }

      function mapVal(value, inMin, inMax, outMin, outMax)
      {
        return Math.floor((value - inMin) * (outMax - outMin) / (inMax - inMin) + outMin);
      }

      function rgbToHex(r, g, b) {
          return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
      }
      function componentToHex(c) {
      var hex = c.toString(16);
      return hex.length == 1 ? "0" + hex : hex;
      }


var locationsJSON = [
{lat: 32.711734697964815,lng: -117.1573294130409, state: "filled", chance: .1},
{lat: 32.711637,lng: -117.15732968838816, state: "filled", chance: .2},
{lat: 32.712659006783944,lng: -117.15774908166627, state: "filled", chance: .3},
{lat: 32.712659006783944,lng: -117.15759944262093, state: "filled", chance: .4},
{lat: 32.71194998427059,lng: -117.15645102889678, state: "filled", chance: .5},
{lat: 32.71178900570318,lng: -117.15645102889678, state: "filled", chance: .5},
{lat: 32.712537,lng: -117.15810168848896, state: "filled", chance: .6},
{lat: 32.712538395929634,lng: -117.15794472460439, state: "filled", chance: .7},
{lat: 32.712538,lng: -117.15802541309337, state: "filled", chance: .7},
{lat: 32.712536,lng: -117.1581741015821, state: "filled", chance: .8},
{lat: 32.712860899321605,lng: -117.15733506885326, state: "filled", chance: .9},
{lat: 32.712798,lng: -117.15733606885252, state: "filled", chance: .95},
{lat: 32.71274,lng: -117.15733506885181, state: "open", chance: .1},
{lat: 32.71254177829138,lng: -117.15726376071609, state: "open", chance: .2},
{lat: 32.71254177829138,lng: -117.15719962977775, state: "open", chance: .4},
{lat: 32.71242603391972,lng: -117.15839093455304, state: "open", chance: .7},
{lat: 32.711587503391975,lng: -117.156786, state: "open", chance: .9},
{lat: 32.711613,lng: -117.156719, state: "open", chance: .1},
{lat: 32.71161330203518,lng: -117.15665706883784, state: "open", chance: .1},
{lat: 32.711446979648166,lng: -117.15721269587777, state: "open", chance: .8}];

});
