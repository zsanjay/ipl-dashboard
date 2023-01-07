import { React } from 'react';
import { Link } from 'react-router-dom';
import './YearSelector.scss'; 

export const YearSelector = ({teamName}) => {

    let years = [];
    const startYear = process.env.REACT_APP_DATA_START_YEAR;
    const endYear = process.env.REACT_APP_DATA_END_YEAR;
    const yearRoute = `/teams/${teamName}/matches/`;

    for(let i = startYear; i <= endYear; i++) {
        years.push(i);
    }

    return (
        <ol className="YearSelector">
            {years.map((year , index) => <li key={index}><Link to={yearRoute + year}>{year}</Link></li> )}
        </ol>
    )
}