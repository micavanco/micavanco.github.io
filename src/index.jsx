import React, { Component } from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { createStore, applyMiddleware } from "redux";
import ReduxPromise from "redux-promise";

import MovieList from './containers/movie-list.js';
import Categories from './containers/categories.js';
import SearchBar from './containers/search-bar.js';
import reducers from './reducers';

class App extends Component {
  render() {
    return (
      <div>
        <SearchBar />
        <Categories />
        <MovieList />
      </div>
    );
  }
}

const createStoreWithMiddleware = applyMiddleware(ReduxPromise)(createStore);

ReactDOM.render(
  <Provider
    store={createStoreWithMiddleware(
      reducers,
      window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
    )}
  >
    <App />
  </Provider>,
  document.querySelector(".container")
);
