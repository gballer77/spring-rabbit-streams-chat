import {MapContainer, Marker, Popup, TileLayer} from 'react-leaflet'
import {LatLngExpression} from "leaflet";
import {MapMarker} from "./Types.ts";

const Map = ({markers}: { markers: MapMarker[] }) => {
  const position: LatLngExpression = [30.2672, -97.7431];
  return (
    <MapContainer center={position} zoom={5} scrollWheelZoom={false} className='h-[400px] w-[400px]'>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      {markers.map((marker, i) => <Marker key={'marker-' + i} position={marker.position}>
        <Popup>{marker.message}</Popup>
      </Marker>)}
    </MapContainer>
  );
};

export default Map;