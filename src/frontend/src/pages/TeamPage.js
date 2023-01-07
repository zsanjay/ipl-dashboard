import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { useParams } from 'react-router-dom';
import React, { useEffect , useState} from 'react';
import PieChart from 'react-pie-graph-chart';
import './TeamPage.scss';
import { Link } from 'react-router-dom';

export const TeamPage = () => {

  const[team , setTeam] = useState({matches : []});
  const { teamName } = useParams();
  const[pieData , setPieData] = useState([]);

  useEffect(
    () => {
        const fetchMatches = async () => {
            const response = await fetch(`http://localhost:8080/team/${teamName}`);
            const data = await response.json();
            setTeam(data);
            setPieData([
              {
                type: "Losses",
                value: data.totalMatches - data.totalWins,
                color: "#a34d5d"
              },
              {
                type: "Wins",
                value: data.totalWins,
                color: "#4da375"
              }
            ])
        };
        fetchMatches();
    },[teamName]);

    if(!team || !team.teamName) {
      return <h1>Team Not Found</h1>
    }

    // const data = [
    //   {
    //     type: "Losses",
    //     value: team.totalMatches - team.totalWins,
    //     color: "#a34d5d"
    //   },
    //   {
    //     type: "Wins",
    //     value: team.totalWins,
    //     color: "#4da375"
    //   }
    // ];

  return (
    <div className="TeamPage">
    <div className="team-name-section">  
    <h1 className='team-name'>{team.teamName}</h1>
    </div>
    <div className="win-loss-section">
      <h3>Win / Losses</h3>
      <PieChart data={pieData}/>
    </div>
    <div className="match-detail-section">
    <h3>Latest Matches</h3>  
    <MatchDetailCard teamName={team.teamName} match={team.matches[0]}/>
    </div>
      {team.matches.slice(1).map((match , index) => <MatchSmallCard key={index} match={match} teamName={team.teamName}/>)}
      <div className="more-link">
      <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More ></Link> 
      </div>
    </div>
  );
}
