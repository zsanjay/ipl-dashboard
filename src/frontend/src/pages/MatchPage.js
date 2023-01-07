import React, { useEffect , useState} from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { useParams } from 'react-router-dom';
import { YearSelector } from '../components/YearSelector';
import './MatchPage.scss';

export const MatchPage = () => {

    const[match , setMatches] = useState([]);
    const { teamName , year } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                const data = await response.json();
                setMatches(data);
            };
            fetchMatches();
        },[teamName , year]); 

    if(match.length === 0) {
        
        return (
            <div className="MatchPage">
            <div className="year-selector">
             <h3>Select Year</h3>   
            <YearSelector teamName = {teamName}/>
            </div>
            <div>
            <h1>Match Page</h1>
            <h1>No Match Found For Year {year}</h1>
            </div>
        </div> 
        )
    }    

    return (
        <div className="MatchPage">
            <div className="year-selector">
             <h3>Select Year</h3>   
            <YearSelector teamName = {teamName}/>
            </div>
            <div>
            <h1 className="page-heading">{teamName} matches in {year}</h1>
            {
                match.map((match , index) => <MatchDetailCard key={index} teamName={teamName} match={match}/>)
            }
            </div>
        </div>    
    );
}