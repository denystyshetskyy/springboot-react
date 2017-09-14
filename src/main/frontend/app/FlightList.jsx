import React from 'react';
import Flight from './Flight.jsx';
export default class FlightList extends React.Component {
  constructor(props) {
    super(props);

    this.state = {flights: []};
  }

  componentDidMount() {
    fetch(`http://localhost:8095/v2/flights`)
                .then(result.=> {
                    this.setState({flights:result.json()});
                });
  }

  render() {
    var flightElements = this.state.flights.map(flight => <Flight flight={flight}/>);

      return (
      			<table>
      				<tbody>
      					<tr>
      						<th>Name</th>
      						<th>Destination</th>
      						<th>Departure</th>
      					</tr>
      					{flightElements}
      				</tbody>
      			</table>
      		);


  }
}