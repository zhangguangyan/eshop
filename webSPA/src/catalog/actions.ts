//----actions
const REQUEST_ALL = 'REQUEST_ALL';
const RECEIVE_ALL = 'RECEIVE_ALL';

//async action
const retrieveAll = (url: string) => dispatch => {
    dispatch({ type: REQUEST_ALL });
    return fetch(url)
        .then(res => res.json())
        .then(response => {
            dispatch({
                type: RECEIVE_ALL,
                payload: response
            });
        })
        .catch(error => console.error('Error:', error));
};

export {
    REQUEST_ALL,
    RECEIVE_ALL,
    retrieveAll
}
