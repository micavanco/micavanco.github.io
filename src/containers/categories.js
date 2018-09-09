import React,{ Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { fetchData } from '../actions/index.js';
import { favoriteData } from '../actions/get-favorites.js';
import { fetchSearch } from '../actions/search-action.js';

class Categories extends Component {
  constructor(props) {
    super(props);

    const movies = new Array();
    const added = new Array();
    localStorage.setItem('movies', movies);
    localStorage.setItem('added', added);
    localStorage.setItem('28', 'Action');
    localStorage.setItem('12', 'Adventure');
    localStorage.setItem('16', 'Animation');
    localStorage.setItem('35', 'Comedy');

    localStorage.setItem('80', 'Crime');
    localStorage.setItem('99', 'Documentary');
    localStorage.setItem('18', 'Drama');
    localStorage.setItem('10751', 'Family');

    localStorage.setItem('14', 'Fantasy');
    localStorage.setItem('36', 'History');
    localStorage.setItem('27', 'Horror');
    localStorage.setItem('10402', 'Music');

    localStorage.setItem('9648', 'Mystery');
    localStorage.setItem('10749', 'Romance');
    localStorage.setItem('878', 'Science Fiction');
    localStorage.setItem('10770', 'TV Movie');

    localStorage.setItem('53', 'Thriller');
    localStorage.setItem('10752', 'War');
    localStorage.setItem('37', 'Western');


    this.onClickHandle = this.onClickHandle.bind(this);
  }

  componentWillMount(){
    this.props.fetchData('popular');
  }

  onClickHandle(e){
    if(e.target.id === 'favorites')
      this.props.favoriteData();
    else if(e.target.id === 'search')
      this.props.fetchSearch(document.getElementById('search-input').value);
    else
      this.props.fetchData(e.target.id);
    Array.from(e.target.parentNode.childNodes).map((li)=>{
        if(li.classList.contains('selected-category')) {
          li.classList.remove('selected-category');
        }
      });
    e.target.classList.add('selected-category');
  }

  render () {
    return (
      <div>
        <ul className="categories">
          <li id="popular" className="category-item selected-category" onClick={this.onClickHandle}>Popular</li>
          <li id="top_rated" className="category-item" onClick={this.onClickHandle}>Top Rated</li>
          <li id="now_playing" className="category-item" onClick={this.onClickHandle}>Now Playing</li>
          <li id="upcoming" className="category-item" onClick={this.onClickHandle}>Up Coming</li>
          <li id="favorites" className="category-item" onClick={this.onClickHandle}>My Favorites</li>
          <li id="search" className="category-item" onClick={this.onClickHandle}>Search</li>
        </ul>
      </div>
    );
  }
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators( { fetchData, favoriteData, fetchSearch }, dispatch);
}

export default connect(null, mapDispatchToProps)(Categories);
