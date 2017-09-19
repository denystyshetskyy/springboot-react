import React from 'react';
import Flight from './Flight.js';
export default class FlightList extends React.Component {
  constructor(props) {
    super(props);

    this.state = {flightsData: []};
  }

  componentDidMount() {
    fetch('http://localhost:8095/v2/flights')
                .then(result => {return result.json();}).then(flights =>
                {

                      var flightsTmp = flights.map(flight =>
                      {
                        return (<tr><th>{flight.name}</th>
                                <th>{flight.destination}</th>
                                <th>{flight.departure}</th></tr>)
                      })


                    this.setState({flightsData:flightsTmp});
                    console.log('flights', this.state.flightsData);
                });
    }



  render() {
  console.log('In Render');
//    var flightElements = this.state.flightsData.map(flight => <Flight flight={flight}/>);

      return (
      			<table>
      				<tbody>
      					<tr>
      						<th>Name</th>
      						<th>Destination</th>
      						<th>Departure</th>
      					</tr>
      					<tr>
      					    <th>
      					        {this.state.flightsData}
      					    </th>
      					</tr>
      				</tbody>
      			</table>
      		);


  }
}