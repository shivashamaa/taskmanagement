var result = document.getElementById("json-result");


function loadMap() {
	
   var latlng = new google.maps.LatLng(0, 0);

    var myOptions = {
      zoom: 4,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
	var infoWindow = new google.maps.InfoWindow();
	
	//pointing the location
	var marker = new google.maps.Marker({
      position: latlng, 
animation: google.maps.Animation.BOUNCE,
      map: map
    }); 

//for button
  const locationButton = document.createElement("button");
 locationButton.textContent = "Pan to Current Location";
 locationButton.classList.add("custom-map-control-button");
  map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);

locationButton.addEventListener("click", () => {
	
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        position => {
        	const pos = {lat: position.coords.latitude,lng: position.coords.longitude};
			
         infoWindow.setPosition(pos);

		var bdcApi = "https://api.bigdatacloud.net/data/reverse-geocode-client"

		const Http = new XMLHttpRequest();
		Http.open("GET", bdcApi);
        Http.send();
        Http.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                result = JSON.parse(this.responseText);
				var elem = document.getElementById('textBox');
				elem.innerHTML = result.countryName+" , "+result.principalSubdivision+" , "+result.city;
//			infoWindow.setContent(result.countryName+"\n , "+result.principalSubdivision+" , "+result.city);
 			}
        };
		
//		console.log(map.getZoom())
		marker.setPosition({lat: position.coords.latitude,lng: position.coords.longitude});

		// Center map to user's position.
        map.panTo({lat: position.coords.latitude,lng: position.coords.longitude});
//        infoWindow.open(map);
        map.setCenter(pos);

        },
        () => {
          handleLocationError(true, infoWindow, map.getCenter());
        }
      );
    }
});

var time = 500;
map.addListener("center_changed", () => {
    window.setTimeout(() => {
    map.panTo(marker.getPosition());
	map.setZoom(map.getZoom()+1);
	if(time > 0){
		map.setCenter(marker.getPosition());
		marker.setAnimation(null);
	}
	if(time <= 0){
		marker.setAnimation(null);
	}
	time = time -100;
    }, time);
  });
}