    import React from 'react';

export default class FlightSearch extends React.Component {
  constructor(props) {
    super();
    this.state = {
      // Takes active tab from props if it is defined there
      activeTab: props.activeTab || 1
    };
  }
        render(){
            return (
                <div>
                    <input type="text" />
                    <select>
                        <option value="software">Apps</option>
                        <option value="movie">Films</option>
                    </select>
                    <input type="submit" />
                </div>
            );
        }
}