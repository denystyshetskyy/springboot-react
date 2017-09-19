import React from 'react';
class Flight extends React.Component{
	render() {
		    console.log('In flight');
		return (
			<tr>
				<td>{this.props.flight.name}</td>
			</tr>
		)
	}
}