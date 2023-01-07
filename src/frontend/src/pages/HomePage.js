import { TeamTile } from '../components/TeamTile';
import React, { useEffect , useState} from 'react';
import './HomePage.scss';

export const HomePage = () => {

  const[team , setTeam] = useState([]);

  useEffect(
    () => {
        const fetchAllTeams = async () => {
            const response = await fetch('http://localhost:8080/team');
            const data = await response.json();
            setTeam(data);
        };
        fetchAllTeams();
    },[]);


  return (
    <div className="HomePage">
    <div className="header-section">  
    <h1 className='app-name'>IPL Dashboard</h1>
    </div>
    <div className="team-grid">
        {team.map((team , index) => <TeamTile key={index} teamName = {team.teamName}/>)}
    </div>
    </div>
  );
}
