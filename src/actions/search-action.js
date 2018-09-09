import axios from 'axios';

export const FETCH_SEARCH = 'FETCH_SEARCH';
const API_KEY = "fd067333da9722a67e0a78739ccecbf1";

export function fetchSearch(search) {
  let url, request;
  if(search)
  {
    search.replace(' ', '+');
    url = `https://api.themoviedb.org/3/search/movie?api_key=${API_KEY}&language=en-US&query=${search}`;
    request = axios.get(url);
  }
  else
    request = {data: {results: []}};



  return {
    type: FETCH_SEARCH,
    payload: request
  }
}
