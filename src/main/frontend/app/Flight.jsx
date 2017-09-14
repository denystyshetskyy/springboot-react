import React from 'react';
class Flight extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.flight.name}</td>
				<td>{this.props.flight.destination}</td>
				<td>{this.props.employee.departure}</td>
			</tr>
		)
	}
}