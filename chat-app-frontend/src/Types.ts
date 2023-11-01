import {LatLngExpression} from "leaflet";

export type Message = {
  name: string;
  message: string;
}

export type MapMarker = {
  message: string;
  position: LatLngExpression;
}