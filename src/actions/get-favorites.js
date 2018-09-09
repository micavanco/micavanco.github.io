
export const FAVORITE_MOVIES = 'FAVORITE_MOVIES';

export function favoriteData() {

   const array = JSON.parse('['+localStorage.getItem('movies')+']');


  return {
    type: FAVORITE_MOVIES,
    payload: array
  }
}
