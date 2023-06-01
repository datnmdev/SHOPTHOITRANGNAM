export {initMap, setMarker, getLatLngFromAddress}

function initMap(HTMLId) {
    var mapProp= {
        center: new google.maps.LatLng(51.508742,-0.120850),
        zoom: 15
    };
    const map = new google.maps.Map(document.getElementById(`${HTMLId}`), mapProp);
    var marker = new google.maps.Marker({
        position: mapProp.center,
        animation:google.maps.Animation.BOUNCE
    });
    marker.setMap(map);
    return map
}

function setMarker(addressString, map) {
    var location = getLatLngFromAddress(addressString)
    if (location) {
        map.setCenter(new google.maps.LatLng(location.lat(), location.lng()));
        var marker = new google.maps.Marker({
            position: mapProp.center,
            animation:google.maps.Animation.BOUNCE
        });

        marker.setMap(map);
    } else {
        alert('Không tìm thấy tọa độ nào có địa chỉ: ' + addressString)
    }
}

function getLatLngFromAddress(addressString) {
    // Tạo một đối tượng Geocoder để chuyển đổi địa chỉ thành tọa độ
    var geocoder = new google.maps.Geocoder();
    var coordinates = null
    
    // Sử dụng phương thức geocode() để lấy tọa độ của địa chỉ
    geocoder.geocode({ address: addressString }, function(results, status) {
        if (status === 'OK') {
            let latLng = results[0].geometry.location
            coordinates = new google.maps.LatLng(latLng.lat(), latLng.lng())
        }
    });
    return coordinates
}