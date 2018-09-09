import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { fetchSearch } from '../actions/search-action.js';


class SearchBar extends Component {

  constructor(props) {
    super(props);

    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  onFormSubmit(e) {
    e.preventDefault();
    let temp = document.querySelector('.categories').childNodes;
    Array.from(temp).map((li)=>{
      if(li.classList.contains('selected-category')) {
        li.classList.remove('selected-category');
      }
    });
    document.getElementById('search').classList.add('selected-category');
    if(e.target.value)
      this.props.fetchSearch(e.target.value);
  }

  Focus(e)
  {
    e.target.placeholder = '';
  }

  Blur(e)
  {
    e.target.placeholder = 'Search movie title...';
  }

  render() {
    return (
      <div className="navbar" >
        <h2>React Movie App</h2>
        <input
          id="search-input"
          type="text"
          className="from-control search-bar"
          placeholder="Search movie title..."
          onChange={this.onFormSubmit}
          onFocus={this.Focus}
          onBlur={this.Blur}
        />
      </div>
    );
  }
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({fetchSearch}, dispatch);
}

export default connect(null, mapDispatchToProps)(SearchBar);
