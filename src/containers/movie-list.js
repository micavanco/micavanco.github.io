import React, { Component } from 'react';
import { connect } from 'react-redux';
import MoviePopUp from './movie-pop-ups';

class MovieList extends Component {
  constructor(props)
  {
    super(props);

    this.state = { currentElement: null};
  }


  movieItems() {
    if(this.props.movies){
      if(!this.props.movies.length)
        if(!document.getElementById('search-input').value)
          return <h1 id="search-info">Type title in search bar...</h1>;
        else
          return <h1 id="search-info">I'm sorry... We didn't find your movie ;(</h1>;
      else
        return this.props.movies.map((movie) => {
          if(movie.poster_path)
            return <li className="movie-list-item" key={movie.id} onMouseEnter={this.onHover.bind(this)} onMouseLeave={this.onLeave.bind(this)}>
              {<MoviePopUp movie={movie} />}
              {<img className="poster" src={`https://image.tmdb.org/t/p/w300${movie.poster_path}`}/>}
              </li>;

        });
    }
}

onHover(e)
{
  this.state.currentElement = e.target.parentElement.firstElementChild;
  if(this.state.currentElement.className === 'movie-pop-ups')
    this.state.currentElement.style.display = 'block';
  else
    this.state.currentElement = null;
}

onLeave(e)
{
  if(this.state.currentElement)
    this.state.currentElement.style.display = 'none';
}

  render() {
    return (
      <div>
        <ul className="list-container">
          {this.movieItems()}
        </ul>
        <div id="description-container"><div id="description-background"></div><div id="description-box"></div></div>
      </div>
    );
  }
}

function mapStateToProps(state){
  return {
    movies: state.movies
  };
}

export default connect(mapStateToProps)(MovieList);



